package online.nasgar.hubcore.hubcore.adapter;

import me.clip.placeholderapi.PlaceholderAPI;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.managers.basis.MessageManager;
import online.nasgar.hubcore.hubcore.utils.Utils;
import online.nasgar.hubcore.hubcore.managers.basis.scoreboard.AssembleAdapter;
import online.nasgar.hubcore.hubcore.managers.basis.scoreboard.AssembleStyle;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardAdapter implements AssembleAdapter {

    private final MessageHandler messageHandler = MessageManager.getMessageHandler();

    @Override
    public String getTitle(Player player) {
        return Utils.translate(messageHandler.replacing(player,"scoreboard.title"));
    }

    @Override
    public List<String> getLines(Player player) {
        String playerName = player.getDisplayName();
        String vaultPrefix = "%vault_prefix%";

        return Utils.translate(PlaceholderAPI.setPlaceholders(
                player.getPlayer(),
                messageHandler.replacingMany(
                        player, "scoreboard.lines",
                        "%player%", playerName,
                        "%rank%",PlaceholderAPI.setPlaceholders(player, vaultPrefix))));
    }

    @Override
    public AssembleStyle getStyle() {
        return AssembleStyle.MODERN;
    }

}
