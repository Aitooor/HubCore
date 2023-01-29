package online.nasgar.hubcore.hubcore;

import online.nasgar.hubcore.hubcore.managers.MessageManager;
import online.nasgar.hubcore.hubcore.managers.PluginManager;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class HubCore extends JavaPlugin {

    private static HubCore instance;
    private final MessageManager messageManager = new MessageManager(this);

    @Override
    public void onEnable() {
        instance = this;
        new PluginManager(this, messageManager).setupPlugin();
    }

    @Override
    public void onDisable() {
        instance = null;
        Utils.log("&cDisabled Correctly");
    }

    public static HubCore getInstance() {
        return instance;
    }
}
