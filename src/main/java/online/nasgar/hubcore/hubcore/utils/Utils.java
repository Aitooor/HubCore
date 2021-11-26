package online.nasgar.hubcore.hubcore.utils;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface Utils {

    static void log(String... args){
        for(String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + str));
    }

    static void logError(String... args){
        for(String str : args){
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + "[ERROR] &c" + str));
        }
    }

    static @NotNull String ct(String source){
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    static void send(CommandSender sender, String prefix, String... args) {
        for (String arg : args) {
            sender.sendMessage(ct(prefix + arg));
        }
    }

    static String getPrefix(){
        HubCore hubCore = HubCore.getPlugin(HubCore.class);
        return  "[" + hubCore.getName() + "] ";
    }

    static void loadFiles(HubCore hubCore, String... files){
        for(String name : files) {
            if (hubCore.getResource(name) != null) {
                hubCore.saveResource(name, false);
            }
        }
    }
}
