package com.github.sakiio.provider.board;

import com.github.sakiio.provider.board.events.AssembleBoardCreatedEvent;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.scoreboard.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class AssembleBoard
{
    private final Assemble assemble;
    private final List<AssembleBoardEntry> entries;
    private final List<String> identifiers;
    private final UUID uuid;
    
    public AssembleBoard(final Player player, final Assemble assemble) {
        this.entries = new ArrayList<AssembleBoardEntry>();
        this.identifiers = new ArrayList<String>();
        this.uuid = player.getUniqueId();
        this.assemble = assemble;
        this.setup(player);
    }
    
    public Scoreboard getScoreboard() {
        final Player player = Bukkit.getPlayer(this.uuid);
        if (this.assemble.isHook() || player.getScoreboard() != Bukkit.getScoreboardManager().getMainScoreboard()) {
            return player.getScoreboard();
        }
        return Bukkit.getScoreboardManager().getNewScoreboard();
    }
    
    public Objective getObjective() {
        final Scoreboard scoreboard = this.getScoreboard();
        if (scoreboard.getObjective("Assemble") == null) {
            final Objective objective = scoreboard.registerNewObjective("Assemble", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(this.assemble.getAdapter().getTitle(Bukkit.getPlayer(this.uuid)));
            return objective;
        }
        return scoreboard.getObjective("Assemble");
    }
    
    private void setup(final Player player) {
        final Scoreboard scoreboard = this.getScoreboard();
        player.setScoreboard(scoreboard);
        this.getObjective();
        final AssembleBoardCreatedEvent createdEvent = new AssembleBoardCreatedEvent(this);
        Bukkit.getPluginManager().callEvent((Event)createdEvent);
    }
    
    public AssembleBoardEntry getEntryAtPosition(final int pos) {
        return (pos >= this.getEntries().size()) ? null : this.getEntries().get(pos);
    }
    
    public String getUniqueIdentifier(final int position) {
        String identifier;
        for (identifier = String.valueOf(getRandomChatColor(position)) + ChatColor.WHITE; this.getIdentifiers().contains(identifier); identifier = String.valueOf(identifier) + getRandomChatColor(position) + ChatColor.WHITE) {}
        if (identifier.length() > 16) {
            return this.getUniqueIdentifier(position);
        }
        this.getIdentifiers().add(identifier);
        return identifier;
    }
    
    private static String getRandomChatColor(final int position) {
        return ChatColor.values()[position].toString();
    }
    
    public List<AssembleBoardEntry> getEntries() {
        return this.entries;
    }
    
    public List<String> getIdentifiers() {
        return this.identifiers;
    }
}
