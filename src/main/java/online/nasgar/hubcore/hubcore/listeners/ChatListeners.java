package online.nasgar.hubcore.hubcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class ChatListeners implements Listener {

    public ChatListeners() {
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String rank = "%vault_prefix% ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        event.setFormat(Utils.ct(PlaceholderAPI.setPlaceholders(player,rank) + player.getDisplayName() + "&7: &r" + event.getMessage()));
    }
}
