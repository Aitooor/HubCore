package com.github.sakiio.provider.board;

import com.github.sakiio.provider.board.events.AssembleBoardCreateEvent;
import com.github.sakiio.provider.board.events.AssembleBoardDestroyEvent;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class AssembleListener implements Listener
{
    private final Assemble assemble;
    
    public AssembleListener(final Assemble assemble) {
        this.assemble = assemble;
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final AssembleBoardCreateEvent createEvent = new AssembleBoardCreateEvent(event.getPlayer());
        Bukkit.getPluginManager().callEvent(createEvent);
        if (createEvent.isCancelled()) {
            return;
        }
        this.assemble.getBoards().put(event.getPlayer().getUniqueId(), new AssembleBoard(event.getPlayer(), this.assemble));
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final AssembleBoardDestroyEvent destroyEvent = new AssembleBoardDestroyEvent(event.getPlayer());
        Bukkit.getPluginManager().callEvent(destroyEvent);
        if (destroyEvent.isCancelled()) {
            return;
        }
        this.assemble.getBoards().remove(event.getPlayer().getUniqueId());
        event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
}
