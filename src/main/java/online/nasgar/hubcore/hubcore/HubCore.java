package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import me.yushust.message.MessageHandler;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.hubcore.hubcore.adapter.ScoreboardAdapter;
import online.nasgar.hubcore.hubcore.commands.HubCoreCMD;
import online.nasgar.hubcore.hubcore.commands.menus.PremiumSelectorCMD;
import online.nasgar.hubcore.hubcore.commands.menus.SelectorsCMD;
import online.nasgar.hubcore.hubcore.listeners.ItemJoinListeners;
import online.nasgar.hubcore.hubcore.listeners.menus.SelectorsListener;
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.message.UserLinguist;
import online.nasgar.hubcore.hubcore.message.UserMessageSender;
import online.nasgar.hubcore.hubcore.utils.Message;
import online.nasgar.hubcore.hubcore.utils.Utils;
import online.nasgar.hubcore.hubcore.utils.scoreboard.Assemble;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class HubCore extends JavaPlugin {

    @Getter private static HubCore instance;

    @Getter private MessageHandler messageHandler;

    @Override
    public void onEnable() {
        instance = this;

        loadBanner();

        this.saveDefaultConfig();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Utils.log("&aCMDs Enabled.");
        Utils.log("");
        loadCMD();

        Utils.log("&aLanguages Enabled.");
        Utils.log("");
        loadLanguages();

        Utils.log("&aScoreboard Enabled.");
        Utils.log("");
        this.registerScoreboard();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
            this.getServer().getPluginManager().registerEvents(new ItemJoinListeners(this), this);
            this.getServer().getPluginManager().registerEvents(new SelectorsListener(this), this);
            Utils.log("&aHooked to PlaceholderAPI.");
            Utils.log("");
        } else {
            Utils.logError("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }


        Utils.log(Message.translate("&aENABLED CORRECTLY"));
    }

    @Override
    public void onDisable() { Utils.log(Message.translate("&cDISABLED CORRECTLY")); }

    private void registerScoreboard() {
        Assemble scoreboard = new Assemble(this, new ScoreboardAdapter());
        scoreboard.setTicks(getConfig().getLong("SCOREBOARD.TICKS"));
    }

    public void loadCMD() {

        this.getCommand("hubcore").setExecutor(new HubCoreCMD(this));

        this.getCommand("menus").setExecutor(new SelectorsCMD(this));

        this.getCommand("premium").setExecutor(new PremiumSelectorCMD(this));

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
    }
}
