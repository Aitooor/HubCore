package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import me.yushust.message.MessageHandler;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.hubcore.hubcore.adapter.ScoreboardAdapter;
import online.nasgar.hubcore.hubcore.commands.FlyCMD;
import online.nasgar.hubcore.hubcore.commands.MenuCMD;
import online.nasgar.hubcore.hubcore.commands.ReloadCMD;
import online.nasgar.hubcore.hubcore.commands.bungee.BedwarsCMD;
import online.nasgar.hubcore.hubcore.commands.bungee.MicroBattlesCMD;
import online.nasgar.hubcore.hubcore.commands.bungee.PracticeCMD;
import online.nasgar.hubcore.hubcore.commands.bungee.SurvivalCMD;
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.message.player.liguist.UserLinguist;
import online.nasgar.hubcore.hubcore.message.player.sender.UserMessageSender;
import online.nasgar.hubcore.hubcore.utils.Message;
import online.nasgar.hubcore.hubcore.utils.Utils;
import online.nasgar.hubcore.hubcore.utils.scoreboard.Assemble;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.figt.loafmenus.LoafMenuRegistrar;

import java.io.File;

@Getter
public final class HubCore extends JavaPlugin {

    @Getter private static HubCore instance;
    @Getter private MessageHandler messageHandler;
    private LoafMenuRegistrar loafMenuRegistrar;

    @Override
    public void onEnable() {
        instance = this;

        loadBanner();

        this.saveDefaultConfig();

        Utils.log("&aChatFormat Enabled.");
        Utils.log("");

        Utils.log("&aCMDs Enabled.");
        Utils.log("");
        loadCMD();

        Utils.log("&aMenus Enabled.");
        Utils.log("");
        loadMenus();


        Utils.log("&aLanguages Enabled.");
        Utils.log("");
        loadLanguages();

        Utils.log("&aScoreboard Enabled.");
        Utils.log("");
        this.registerScoreboard();

        Utils.log("&aTAB Enabled.");
        Utils.log("");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

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

    @Override
    public void onDisable() {
        unloadMenus();

        Utils.log(Message.translate("&cDISABLED CORRECTLY"));
    }

    private void registerScoreboard() {
        Assemble scoreboard = new Assemble(this, new ScoreboardAdapter());
        scoreboard.setTicks(getConfig().getLong("SCOREBOARD.TICKS"));
    }

    public void loadCMD() {

        this.getCommand("fly").setExecutor(new FlyCMD(this));

        loadBungeeCMD();

        this.getCommand("hubcore").setExecutor(new ReloadCMD(this));

        saveDefaultConfig();

    }

    private void loadBungeeCMD() {

        this.getCommand("survival").setExecutor(new SurvivalCMD(this));
        this.getCommand("microbattles").setExecutor(new MicroBattlesCMD(this));
        this.getCommand("practice").setExecutor(new PracticeCMD(this));
        this.getCommand("bedwars").setExecutor(new BedwarsCMD(this));

    }

    public void loadMenus() {
        try {
            this.loafMenuRegistrar = new LoafMenuRegistrar(this);
        } catch (InstantiationException e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getCommand("menu").setExecutor(new MenuCMD());
    }

    public void unloadMenus() {
        if (this.loafMenuRegistrar != null) {
            this.loafMenuRegistrar.unregister();
            this.loafMenuRegistrar = null;
        }

        instance = null;
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
