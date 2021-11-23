package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import me.yushust.message.MessageHandler;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.hubcore.hubcore.commands.FlyCMD;
import online.nasgar.hubcore.hubcore.commands.ReloadCMD;
import online.nasgar.hubcore.hubcore.commands.SetSpawnCMD;
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.message.player.liguist.UserLinguist;
import online.nasgar.hubcore.hubcore.message.player.sender.UserMessageSender;
import online.nasgar.hubcore.hubcore.utils.Message;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HubCore extends JavaPlugin {

    @Getter private static HubCore instance;
    @Getter private MessageHandler messageHandler;

    private FlyCMD cmd;

    @Override
    public void onEnable() {
        loadBanner();

        this.saveDefaultConfig();

        loadCMD();

        loadLanguages();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        getLogger().info(Message.translate("ENABLED CORRECTLY"));
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

    private void loadLanguages(){
        MessageSourceDecorator messageSourceDecorator =
                MessageSourceDecorator.decorate(
                        BukkitMessageAdapt.newYamlSource(
                                this,
                                new File(
                                        getDataFolder(),
                                        "languages"
                                )
                        )
                );
        MessageSource messageSource = messageSourceDecorator
                .addFallbackLanguage("en")
                .addFallbackLanguage("es")
                .get();
        Utils.loadFiles(this,"languages/lang_en.yml", "languages/lang_es.yml");
        this.messageHandler = MessageHandler.of(
                messageSource,
                config -> {
                    config.addInterceptor(Utils::ct);

                    config.specify(Player.class)
                            .setMessageSender(new UserMessageSender())
                            .setLinguist(new UserLinguist());
                }
        );
    }

    private void loadBanner() {
        getLogger().info("-------------------------------------");
        getLogger().info("Simple HubCore plugin coded by " + getDescription().getAuthors());
        getLogger().info("for Nasgar Network");
        getLogger().info("");
        getLogger().info("Plugin Version: " + getDescription().getVersion());
        getLogger().info("-------------------------------------");
        getLogger().info("");
        getLogger().info("HUBCORE ENABLING EVERYTHING...");
        getLogger().info("");
        getLogger().info("ChatFormat Enabled.");
        getLogger().info("");
        getLogger().info("CMDs Enabled.");
        getLogger().info(Message.translate("-------------------------------------"));
        getLogger().info(Message.translate("Simple HubCore plugin coded by " + getDescription().getAuthors()));
        getLogger().info(Message.translate("for Nasgar Network"));
        getLogger().info(Message.translate(""));
        getLogger().info(Message.translate("Plugin Version: " + getDescription().getVersion()));
        getLogger().info(Message.translate("-------------------------------------"));
        getLogger().info(Message.translate(""));
        getLogger().info(Message.translate("CARGANDO TODO..."));
        getLogger().info(Message.translate(""));
    }
}
