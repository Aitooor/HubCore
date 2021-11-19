package online.nasgar.hubcore.hubcore.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

import java.util.List;

public class Message {

    public static String PREFIX = "&b&lNASGAR ";

    public static String PREFIX_CONSOLE = "HUBCORE ";

    public static String NO_PERMISSION = PREFIX + "&cYou don't have permissions.";

    public static String NO_CONSOLE = PREFIX_CONSOLE + "Console can't execute this command.";

    public static String NO_PLAYER_FOUND = PREFIX + "&cNot player found.";

    public static String PLAYER_NO_ONLINE = PREFIX + "&cPlayer isn't online.";

    public static String SYNTAX_ERROR = PREFIX + "&cSyntax error, type correctly";

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
