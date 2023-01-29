package online.nasgar.hubcore.hubcore.utils;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class Utils {

    public static String getPrefix(){ return  "[" + HubCore.getPlugin(HubCore.class).getName() + "] "; }

    public static String translate(String source){
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static List<String> translate(final List<String> coll) {
        coll.replaceAll(Utils::translate);
        return coll;
    }

    public static void log(String... args){
        for(String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(translate(getPrefix() + str));
    }

    public static void logError(String... args){
        for(String str : args){
            Bukkit.getServer().getConsoleSender().sendMessage(translate(getPrefix() + "[ERROR] &c" + str));
        }
    }

}
