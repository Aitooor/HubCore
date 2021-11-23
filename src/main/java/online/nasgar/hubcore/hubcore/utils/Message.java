package online.nasgar.hubcore.hubcore.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class Message {

    public static String translate(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String[] translate(final String[] msg) {
        for (int i = 0; i < msg.length; i++) {
            msg[i] = translate(msg[i]);
        }
        return msg;
    }

    public static List<String> translate(final List<String> coll) {
        coll.replaceAll(Message::translate);
        return coll;
    }

}
