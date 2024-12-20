package online.nasgar.hubcore.hubcore.managers;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;

public class PluginManager {

    private final HubCore plugin;

    public PluginManager(HubCore plugin) {
        this.plugin = plugin;
    }

    public void setupPlugin() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Utils.logError("&cCould not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        Utils.log("&a[Hooked to PlaceholderAPI]");
        Utils.log("");

        plugin.saveDefaultConfig();
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");

        Utils.log("&eRegistering everything..");
        Utils.log("");
        new RegisterManager(plugin.getConfig()).registerAll();

        Utils.log("");
        Utils.log("&aEnabled Correctly!");
    }

}
