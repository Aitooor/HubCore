package online.nasgar.hubcore.hubcore.utils;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class Utils {

    public static void log(String... args){
        for(String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(translate(getPrefix() + str));
    }

    public static void logError(String... args){
        for(String str : args){
            Bukkit.getServer().getConsoleSender().sendMessage(translate(getPrefix() + "[ERROR] &c" + str));
        }
    }

    public static String translate(String source){
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static List<String> translate(final List<String> coll) {
        coll.replaceAll(Utils::translate);
        return coll;
    }

    public static String getPrefix(){
        HubCore hubCore = HubCore.getPlugin(HubCore.class);
        return  "[" + hubCore.getName() + "] ";
    }

}
