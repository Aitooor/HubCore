package online.nasgar.hubcore.hubcore.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class Message {

    public static String translate(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> translate(final List<String> coll) {
        coll.replaceAll(Message::translate);
        return coll;
    }

}
