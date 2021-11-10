package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import online.nasgar.hubcore.hubcore.commands.FlyCMD;
import online.nasgar.hubcore.hubcore.commands.SetSpawnCMD;
import online.nasgar.authcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HubCore extends JavaPlugin {

    @Getter private static HubCore instance;

    private PluginManager pm;
    private FlyCMD cmd;

    @Override
    public void onEnable() {
        loadBanner();

        instance = this;

        this.cmd = new FlyCMD(this);
        getCommand("fly").setExecutor(cmd);
        getCommand("flytimer").setExecutor(cmd);

        this.getCommand("setspawn").setExecutor(new SetSpawnCMD());
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadBanner() {
        getLogger().info(Message.translate("-------------------------------------"));
        getLogger().info(Message.translate("Simple HubCore plugin coded by " + getDescription().getAuthors()));
        getLogger().info(Message.translate("for Nasgar Network"));
        getLogger().info(Message.translate(""));
        getLogger().info(Message.translate("Plugin Version: " + getDescription().getVersion()));
        getLogger().info(Message.translate("-------------------------------------"));
        getLogger().info(Message.translate(""));
        getLogger().info(Message.translate("HUBCORE CARGANDO TODO..."));
        getLogger().info(Message.translate(""));
    }
}
