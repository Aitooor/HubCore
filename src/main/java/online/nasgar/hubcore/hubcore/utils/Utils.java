package online.nasgar.hubcore.hubcore.utils;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    static List<String> translate(final List<String> coll) {
        coll.replaceAll(Utils::ct);
        return coll;
    }

    static String getPrefix(){
        HubCore hubCore = HubCore.getPlugin(HubCore.class);
        return  "[" + hubCore.getName() + "] ";
    }

}
