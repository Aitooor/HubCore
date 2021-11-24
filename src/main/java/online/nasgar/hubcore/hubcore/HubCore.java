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

    @Override
    public void onEnable() {
        loadBanner();

        this.saveDefaultConfig();

        Utils.log("&aCMDs Enabled.");
        Utils.log("");
        loadCMD();

        Utils.log("&aLanguages Enabled.");
        Utils.log("");
        loadLanguages();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
            Utils.log("&aHooked to PlaceholderAPI.");
            Utils.log("");
        } else {
            Utils.logError("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }


        Utils.log(Message.translate("&aENABLED CORRECTLY"));
    }

    public void setConfig(String path, Object value) {
        this.getConfig().set(path, value);
    }

    @Override
    public void onDisable() {
        Utils.log(Message.translate("&cDISABLED CORRECTLY"));
    }

    public void loadCMD() {

        instance = this;

        this.getCommand("fly").setExecutor(new FlyCMD(this));

        this.getCommand("setspawn").setExecutor(new SetSpawnCMD(this));

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
        Utils.log("&8---------------------------------------------------");
        Utils.log("&fSimple HubCore plugin coded by &a" + getDescription().getAuthors());
        Utils.log("&ffor Nasgar Network");
        Utils.log("");
        Utils.log("&fPlugin Version &a" + getDescription().getVersion());
        Utils.log("&8---------------------------------------------------");
        Utils.log("");
        Utils.log("&aENABLING EVERYTHING...");
        Utils.log("");
        Utils.log("&aChatFormat Enabled.");
        Utils.log("");
    }
}
