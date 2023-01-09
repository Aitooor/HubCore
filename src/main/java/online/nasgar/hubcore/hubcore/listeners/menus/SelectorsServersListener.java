package online.nasgar.hubcore.hubcore.listeners.menus;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class SelectorsServersListener implements Listener {
    
    private final HubCore plugin;
    
    public SelectorsServersListener(HubCore instance) {
        plugin = instance;
    }
    
    MessageHandler messageHandler = HubCore.getInstance().getMessageHandler();
    
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // SERVER SELECTOR
        if(event.getView().getTitle().equals(messageHandler.replacing(player, "MENUS.SERVERS.TITLE")) && event.getCurrentItem() != null) {
            switch(event.getRawSlot()) {
                case 40: // Close Inv
                    break;
                case 21: // SURVIVAL
                    ByteArrayDataOutput survival = ByteStreams.newDataOutput();
                    survival.writeUTF("Connect");
                    survival.writeUTF("Survival-1");

                    player.sendPluginMessage(plugin, "BungeeCord", survival.toByteArray());
                    break;
                case 23: // WOOL WARS
                    ByteArrayDataOutput bedwars = ByteStreams.newDataOutput();
                    bedwars.writeUTF("Connect");
                    bedwars.writeUTF("WW-HUB");
                    
                    player.sendPluginMessage(plugin, "BungeeCord", bedwars.toByteArray());
                    break;
                default:
                    return;
            }
            player.closeInventory();
        }
    }
    
}
