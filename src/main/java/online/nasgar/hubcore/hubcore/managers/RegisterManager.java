package online.nasgar.hubcore.hubcore.managers;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.adapter.ScoreboardAdapter;
import online.nasgar.hubcore.hubcore.commands.HubCoreCMD;
import online.nasgar.hubcore.hubcore.commands.menus.SelectorsCMD;
import online.nasgar.hubcore.hubcore.listeners.ChatListeners;
import online.nasgar.hubcore.hubcore.listeners.menus.ItemJoinListeners;
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.listeners.menus.ServersSelectorListener;
import online.nasgar.hubcore.hubcore.managers.basis.MessageManager;
import online.nasgar.hubcore.hubcore.managers.basis.TabCompleterManager;
import online.nasgar.hubcore.hubcore.managers.basis.scoreboard.Assemble;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;

public class RegisterManager {

    private final HubCore plugin = HubCore.getInstance();
    private final FileConfiguration config;

    public RegisterManager(FileConfiguration config) {
        this.config = config;
    }

    public void registerLang() {
        Utils.log("  &8Languages &a✓");
        new MessageManager(plugin).setupMessages();
    }

    public void registerScoreboard() {
        Utils.log("  &8Scoreboard &a✓");
        Assemble scoreboard = new Assemble(plugin, new ScoreboardAdapter());
        scoreboard.setTicks(config.getLong("scoreboard.ticks"));
    }

    public void registerCMD() {
        Utils.log("  &8CMDs &a✓");
        plugin.getCommand("hubcore").setExecutor(new HubCoreCMD(plugin));
        plugin.getCommand("menus").setExecutor(new SelectorsCMD());
    }

    public void registerListeners() {
        Utils.log("  &8Listeners &a✓");
        plugin.getServer().getPluginManager().registerEvents(new ChatListeners(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerListeners(plugin, plugin.getConfig()), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ItemJoinListeners(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ServersSelectorListener(plugin), plugin);
    }

    public void registerTabCompleter() {
        TabCompleterManager tc = new TabCompleterManager();
        plugin.getCommand("hubcore").setTabCompleter(tc);
        plugin.getCommand("menus").setTabCompleter(tc);
    }

    public void registerAll() {
        this.registerLang();
        this.registerCMD();
        this.registerListeners();
        this.registerScoreboard();
        this.registerTabCompleter();
    }
}
