package online.nasgar.hubcore.hubcore.adapter;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import online.nasgar.hubcore.hubcore.managers.scoreboard.AssembleAdapter;
import online.nasgar.hubcore.hubcore.managers.scoreboard.AssembleStyle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardAdapter implements AssembleAdapter {

    FileConfiguration config = HubCore.getInstance().getConfig();

    @Override
    public String getTitle(Player player) {
        return Utils.ct(config.getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        String playerName = "%player_name%";
        String online = "%server_online%";
        String bungee_online = "%bungee_total%";
        String vaultPrefix = "%vault_prefix%";
        String localTime = "%localtime_time_yyyy/MM/dd%";

        return Utils.translate(PlaceholderAPI.setPlaceholders(
                player.getPlayer(),
                HubCore.getInstance().getMessageHandler().replacingMany(
                        player, "SCOREBOARD.LINES",
                        "%online%", online,
                        "%bungee_online%", bungee_online,
                        "%player%", playerName,
                        "%rank%",PlaceholderAPI.setPlaceholders(player, vaultPrefix))));
    }

    @Override
    public AssembleStyle getStyle() {
        return AssembleStyle.MODERN;
    }

}
