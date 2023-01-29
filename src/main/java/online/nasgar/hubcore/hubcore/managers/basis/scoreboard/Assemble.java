package online.nasgar.hubcore.hubcore.managers.basis.scoreboard;

import online.nasgar.hubcore.hubcore.managers.basis.scoreboard.events.AssembleBoardCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Assemble {

    private final JavaPlugin plugin;

    private AssembleAdapter adapter;
    private AssembleThread thread;
    private AssembleListener listeners;
    private AssembleStyle assembleStyle = AssembleStyle.MODERN;

    private Map<UUID, AssembleBoard> boards;

    private long ticks = 2;
    private boolean hook = false, debugMode = true, callEvents = true;

    private final ChatColor[] chatColorCache = ChatColor.values();

    public Assemble(JavaPlugin plugin, AssembleAdapter adapter) {
        if (plugin == null) {
            throw new RuntimeException("Assemble can not be instantiated without a plugin instance!");
        }

        this.plugin = plugin;
        this.adapter = adapter;
        this.boards = new ConcurrentHashMap<>();

        this.setup();
    }

    public void setup() {

        this.listeners = new AssembleListener(this);
        this.plugin.getServer().getPluginManager().registerEvents(listeners, this.plugin);

        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }

        for (Player player : this.getPlugin().getServer().getOnlinePlayers()) {

            if (this.isCallEvents()) {
                AssembleBoardCreateEvent createEvent = new AssembleBoardCreateEvent(player);

                Bukkit.getPluginManager().callEvent(createEvent);
                if (createEvent.isCancelled()) {
                    continue;
                }
            }

            getBoards().putIfAbsent(player.getUniqueId(), new AssembleBoard(player, this));
        }

        // Start Thread.
        this.thread = new AssembleThread(this);
    }

    public void cleanup() {
        // Stop thread.
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }

        if (listeners != null) {
            HandlerList.unregisterAll(listeners);
            listeners = null;
        }

        for (UUID uuid : getBoards().keySet()) {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null || !player.isOnline()) {
                continue;
            }

            getBoards().remove(uuid);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public AssembleAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(AssembleAdapter adapter) {
        this.adapter = adapter;
    }

    public AssembleThread getThread() {
        return thread;
    }

    public void setThread(AssembleThread thread) {
        this.thread = thread;
    }

    public AssembleListener getListeners() {
        return listeners;
    }

    public void setListeners(AssembleListener listeners) {
        this.listeners = listeners;
    }

    public AssembleStyle getAssembleStyle() {
        return assembleStyle;
    }

    public void setAssembleStyle(AssembleStyle assembleStyle) {
        this.assembleStyle = assembleStyle;
    }

    public Map<UUID, AssembleBoard> getBoards() {
        return boards;
    }

    public void setBoards(Map<UUID, AssembleBoard> boards) {
        this.boards = boards;
    }

    public long getTicks() {
        return ticks;
    }

    public void setTicks(long ticks) {
        this.ticks = ticks;
    }

    public boolean isHook() {
        return hook;
    }

    public void setHook(boolean hook) {
        this.hook = hook;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isCallEvents() {
        return callEvents;
    }

    public void setCallEvents(boolean callEvents) {
        this.callEvents = callEvents;
    }

    public ChatColor[] getChatColorCache() {
        return chatColorCache;
    }
}
