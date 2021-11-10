package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import online.nasgar.hubcore.hubcore.commands.FlyCMD;
import online.nasgar.hubcore.hubcore.commands.ReloadCMD;
import online.nasgar.hubcore.hubcore.commands.SetSpawnCMD;
import online.nasgar.authcore.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;


import static online.nasgar.hubcore.hubcore.utils.Message.PREFIX_CONSOLE;

public final class HubCore extends JavaPlugin {

    @Getter private static HubCore instance;

    private FlyCMD cmd;

    @Override
    public void onEnable() {
        loadBanner();

        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

        loadCMD();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadCMD() {

        instance = this;

        this.cmd = new FlyCMD(this);
        this.getCommand("fly").setExecutor(cmd);
        this.getCommand("flytimer").setExecutor(cmd);

        this.getCommand("setspawn").setExecutor(new SetSpawnCMD());

        this.getCommand("hubcore").setExecutor(new ReloadCMD(this));

        saveDefaultConfig();

    }

    private void loadBanner() {
        getLogger().info(PREFIX_CONSOLE + "-------------------------------------");
        getLogger().info(PREFIX_CONSOLE + "Simple HubCore plugin coded by " + getDescription().getAuthors());
        getLogger().info(PREFIX_CONSOLE + "for Nasgar Network");
        getLogger().info(PREFIX_CONSOLE + "");
        getLogger().info(PREFIX_CONSOLE + "Plugin Version: " + getDescription().getVersion());
        getLogger().info(PREFIX_CONSOLE + "-------------------------------------");
        getLogger().info("");
        getLogger().info(PREFIX_CONSOLE + "HUBCORE ENABLING EVERYTHING...");
        getLogger().info("");
        getLogger().info(PREFIX_CONSOLE + "ChatFormat Enabled.");
        getLogger().info("");
        getLogger().info(PREFIX_CONSOLE + "CMDs Enabled.");
    }
}
