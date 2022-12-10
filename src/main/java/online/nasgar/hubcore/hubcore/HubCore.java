package online.nasgar.hubcore.hubcore;

import lombok.Getter;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.hubcore.hubcore.adapter.ScoreboardAdapter;
import online.nasgar.hubcore.hubcore.commands.HubCoreCMD;
import online.nasgar.hubcore.hubcore.commands.menus.SelectorsCMD;
import online.nasgar.hubcore.hubcore.listeners.ItemJoinListeners;
import online.nasgar.hubcore.hubcore.listeners.menus.SelectorsListener;
import online.nasgar.hubcore.hubcore.listeners.PlayerListeners;
import online.nasgar.hubcore.hubcore.utils.Utils;
import online.nasgar.hubcore.hubcore.managers.scoreboard.Assemble;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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

        Utils.log("&aEnabling everything");

        this.saveDefaultConfig();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Utils.log("&8CMDs enabled");
        loadCMD();

        Utils.log("&8Languages setup");
        this.setupNMessages();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Utils.log("&8Scoreboard enabled");
            this.registerScoreboard();
            this.getServer().getPluginManager().registerEvents(new PlayerListeners(this, this.getConfig()), this);
            this.getServer().getPluginManager().registerEvents(new ItemJoinListeners(this), this);
            this.getServer().getPluginManager().registerEvents(new SelectorsListener(this), this);
            Utils.log("&aHooked to PlaceholderAPI");
            Utils.log("");
        } else {
            Utils.logError("&cCould not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        Utils.log("&aEnabled Correctly!");
    }

    @Override
    public void onDisable() { Utils.log("&cDisabled Correctly"); }

    private void registerScoreboard() {
        Assemble scoreboard = new Assemble(this, new ScoreboardAdapter());
        scoreboard.setTicks(getConfig().getLong("SCOREBOARD.TICKS"));
    }

    public void loadCMD() {
        this.getCommand("hubcore").setExecutor(new HubCoreCMD(this));
        this.getCommand("menus").setExecutor(new SelectorsCMD(this));

        saveDefaultConfig();
    }

    public void setupNMessages() {
        File langFile = new File(this.getDataFolder(), "lang");
        if (!langFile.exists()) {
            langFile.mkdir();
        }

        MessageProvider messageProvider = MessageProvider
                .create(
                        MessageSourceDecorator
                                .decorate(BukkitMessageAdapt.newYamlSource(this, "lang/lang_%lang%.yml"))
                                .addFallbackLanguage("en")
                                .get(),
                        config -> {
                            config.specify(Player.class)
                                    .setLinguist(player -> player.spigot().getLocale().split("_")[0])
                                    .setMessageSender((sender, mode, message) -> sender.sendMessage(message));
                            config.specify(CommandSender.class)
                                    .setLinguist(commandSender -> "en")
                                    .setMessageSender((sender, mode, message) -> sender.sendMessage(message));
                            config.addInterceptor(s -> ChatColor.translateAlternateColorCodes('&', s));
                            config.specify(ConsoleCommandSender.class)
                                    .setLinguist(commandSender -> "en")
                                    .setMessageSender((sender, mode, message) -> sender.sendMessage(message));
                            config.addInterceptor(s -> ChatColor.translateAlternateColorCodes('&', s));
                        }
                );

        messageHandler = MessageHandler.of(messageProvider);
    }
}
