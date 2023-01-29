package online.nasgar.hubcore.hubcore.managers;

import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class MessageManager {

    private final HubCore plugin;
    private static MessageHandler messageHandler;

    public MessageManager(HubCore plugin) {
        this.plugin = plugin;
    }

    public void setupMessages() {
        File langFile = new File(plugin.getDataFolder(), "lang");
        if (!langFile.exists()) langFile.mkdir();

        MessageProvider messageProvider = MessageProvider
                .create(
                        MessageSourceDecorator
                                .decorate(BukkitMessageAdapt.newYamlSource(plugin, "lang/lang_%lang%.yml"))
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

    public static MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
