package com.github.sakiio.provider.board;

import com.github.sakiio.provider.board.events.AssembleBoardCreateEvent;
import org.bukkit.plugin.java.*;
import java.util.concurrent.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;

public class Assemble
{
    private JavaPlugin plugin;
    private AssembleAdapter adapter;
    private AssembleThread thread;
    private AssembleListener listeners;
    private AssembleStyle assembleStyle;
    private Map<UUID, AssembleBoard> boards;
    private long ticks;
    private boolean hook;
    private boolean debugMode;
    
    public Assemble(final JavaPlugin plugin, final AssembleAdapter adapter) {
        this.assembleStyle = AssembleStyle.MODERN;
        this.ticks = 2L;
        this.hook = false;
        this.debugMode = true;
        if (plugin == null) {
            throw new RuntimeException("Scoreboard can not be instantiated without a plugin instance!");
        }
        this.setPlugin(plugin);
        this.setAdapter(adapter);
        this.setBoards(new ConcurrentHashMap<UUID, AssembleBoard>());
        this.setup();
    }
    
    public void setup() {
        this.listeners = new AssembleListener(this);
        this.getPlugin().getServer().getPluginManager().registerEvents((Listener)this.listeners, (Plugin)this.getPlugin());
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final AssembleBoardCreateEvent createEvent = new AssembleBoardCreateEvent(player);
            Bukkit.getPluginManager().callEvent((Event)createEvent);
            if (createEvent.isCancelled()) {
                return;
            }
            this.getBoards().putIfAbsent(player.getUniqueId(), new AssembleBoard(player, this));
        }
        this.thread = new AssembleThread(this);
    }
    
    public void cleanup() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        if (this.listeners != null) {
            HandlerList.unregisterAll((Listener)this.listeners);
            this.listeners = null;
        }
        for (final UUID uuid : this.getBoards().keySet()) {
            final Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                if (!player.isOnline()) {
                    continue;
                }
                this.getBoards().remove(uuid);
                player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
        }
    }
    
    public long getTicks() {
        return this.ticks;
    }
    
    public void setTicks(final long ticks) {
        this.ticks = ticks;
    }
    
    public JavaPlugin getPlugin() {
        return this.plugin;
    }
    
    public void setPlugin(final JavaPlugin plugin) {
        this.plugin = plugin;
    }
    
    public Map<UUID, AssembleBoard> getBoards() {
        return this.boards;
    }
    
    public void setBoards(final Map<UUID, AssembleBoard> boards) {
        this.boards = boards;
    }
    
    public AssembleAdapter getAdapter() {
        return this.adapter;
    }
    
    public void setAdapter(final AssembleAdapter adapter) {
        this.adapter = adapter;
    }
    
    public AssembleStyle getAssembleStyle() {
        return this.assembleStyle;
    }
    
    public void setAssembleStyle(final AssembleStyle assembleStyle) {
        this.assembleStyle = assembleStyle;
    }
    
    public boolean isHook() {
        return this.hook;
    }
    
    public void setHook(final boolean hook) {
        this.hook = hook;
    }
}
