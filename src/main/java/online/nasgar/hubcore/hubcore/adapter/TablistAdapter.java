package online.nasgar.hubcore.hubcore.adapter;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.managers.basis.MessageManager;
import online.nasgar.hubcore.hubcore.managers.basis.TablistManager;
import org.bukkit.entity.Player;

public class TablistAdapter {

    private HubCore plugin;

    public TablistAdapter(HubCore plugin) {
        this.plugin = plugin;
    }

    public void loadTablist(Player player) {
        TablistManager manager = new TablistManager(plugin, player);
        manager.setHeaders(PlaceholderAPI.setPlaceholders(
                player, MessageManager.getMessageHandler().replacingMany(player, "tab.header")
        ));

        String playerListNames = PlaceholderAPI.setPlaceholders(player, "%vault_prefix% %player_name%");
        playerListNames = PlaceholderAPI.setPlaceholders(player, playerListNames);
        player.setPlayerListName(playerListNames);

        manager.setFooters(PlaceholderAPI.setPlaceholders(
                player, MessageManager.getMessageHandler().replacingMany(player, "tab.footer")
        ));
        manager.showTab();
    }

}
