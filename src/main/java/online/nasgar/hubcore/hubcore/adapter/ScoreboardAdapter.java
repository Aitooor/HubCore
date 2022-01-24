package online.nasgar.hubcore.hubcore.adapter;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Message;
import online.nasgar.hubcore.hubcore.utils.scoreboard.AssembleAdapter;
import online.nasgar.hubcore.hubcore.utils.scoreboard.AssembleStyle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardAdapter implements AssembleAdapter {

    FileConfiguration config = HubCore.getInstance().getConfig();

    @Override
    public String getTitle(Player player) {
        return Message.translate(config.getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        String playerName = "%player_name%";
        playerName = PlaceholderAPI.setPlaceholders(player.getPlayer(), playerName);
        String online = "%server_online%";
        online = PlaceholderAPI.setPlaceholders(player.getPlayer(), online);
        String bungee_online = "%bungee_total%";
        bungee_online = PlaceholderAPI.setPlaceholders(player.getPlayer(), bungee_online);
        String vaultPrefix = "%vault_prefix%";
        vaultPrefix = PlaceholderAPI.setPlaceholders(player.getPlayer(), vaultPrefix);

        return Message.translate(HubCore.getInstance().getMessageHandler().replacingMany(player, "SCOREBOARD.LINES", "%online%", online, "%bungee_online%", bungee_online, "%player%", playerName, "%rank%", vaultPrefix));
    }

    @Override
    public AssembleStyle getStyle() {
        return AssembleStyle.MODERN;
    }

}
