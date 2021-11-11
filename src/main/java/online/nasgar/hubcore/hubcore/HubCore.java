package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import online.nasgar.hubcore.hubcore.commands.FlyCMD;
import online.nasgar.hubcore.hubcore.commands.ReloadCMD;
import online.nasgar.hubcore.hubcore.commands.SetSpawnCMD;
<<<<<<< Updated upstream
import online.nasgar.authcore.listeners.PlayerListeners;
=======
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.plugin.PluginManager;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        loadCMD();
=======
        getLogger().info(Message.translate("HUBCORE ENABLED CORRECTLY"));
>>>>>>> Stashed changes
    }

    public void setConfig(String path, Object value) {
        this.getConfig().set(path, value);
    }

    @Override
    public void onDisable() {
        getLogger().info(Message.translate("DISABLED CORRECTLY"));
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
<<<<<<< Updated upstream
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
=======
        getLogger().info(Message.translate("-------------------------------------"));
        getLogger().info(Message.translate("Simple HubCore plugin coded by " + getDescription().getAuthors()));
        getLogger().info(Message.translate("for Nasgar Network"));
        getLogger().info(Message.translate(""));
        getLogger().info(Message.translate("Plugin Version: " + getDescription().getVersion()));
        getLogger().info(Message.translate("-------------------------------------"));
        getLogger().info(Message.translate(""));
        getLogger().info(Message.translate("CARGANDO TODO..."));
        getLogger().info(Message.translate(""));
>>>>>>> Stashed changes
    }
}