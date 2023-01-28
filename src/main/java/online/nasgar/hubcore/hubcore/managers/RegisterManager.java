package online.nasgar.hubcore.hubcore.managers;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.adapter.ScoreboardAdapter;
import online.nasgar.hubcore.hubcore.commands.HubCoreCMD;
import online.nasgar.hubcore.hubcore.commands.menus.SelectorsCMD;
import online.nasgar.hubcore.hubcore.listeners.ChatListeners;
import online.nasgar.hubcore.hubcore.listeners.ItemJoinListeners;
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.listeners.menus.SelectorsServersListener;
import online.nasgar.hubcore.hubcore.managers.scoreboard.Assemble;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;

public class RegisterManager {

    private HubCore plugin = HubCore.getInstance();
    private FileConfiguration config;
    private MessageManager messageManager;

    public RegisterManager(FileConfiguration config, MessageManager messageManager) {
        this.config = config;
        this.messageManager = messageManager;
    }

    public void registerLang() {
        Utils.log("  &8Languages &a✓");
        new MessageManager(plugin).setupMessages();
    }

    public void registerScoreboard() {
        Utils.log("  &8Scoreboard &a✓");
        Assemble scoreboard = new Assemble(plugin, new ScoreboardAdapter());
        scoreboard.setTicks(config.getLong("SCOREBOARD.TICKS"));
    }

    public void registerCMD() {
        Utils.log("  &8CMDs &a✓");
        plugin.getCommand("hubcore").setExecutor(new HubCoreCMD(plugin, messageManager));
        plugin.getCommand("menus").setExecutor(new SelectorsCMD(plugin, messageManager));
    }

    public void registerListeners() {
        Utils.log("  &8Listeners &a✓");
        plugin.getServer().getPluginManager().registerEvents(new ChatListeners(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerListeners(plugin, plugin.getConfig()), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ItemJoinListeners(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SelectorsServersListener(plugin), plugin);
    }

    public void registerAll() {
        this.registerLang();
        this.registerCMD();
        this.registerListeners();
        this.registerScoreboard();
    }
}