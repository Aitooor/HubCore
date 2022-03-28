package online.nasgar.hubcore.hubcore.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class SelectorsListener implements Listener {
    
    private final HubCore plugin;
    
    public SelectorsListener(HubCore instance) {
        plugin = instance;
    }
    
    MessageHandler messageHandler = HubCore.getInstance().getMessageHandler();
    
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // SERVER SELECTOR
        if(event.getView().getTitle().equals(messageHandler.replacing(player, "MENUS.SERVERS.TITLE")) &&
           event.getCurrentItem() != null)
        {
            switch(event.getRawSlot()) {
                case 40: // Close Inv
                    break;
                case 21: // SURVIVAL
                    ByteArrayDataOutput survival = ByteStreams.newDataOutput();
                    survival.writeUTF("Connect");
                    survival.writeUTF("Survival-1");

                    player.sendPluginMessage(plugin, "BungeeCord", survival.toByteArray());
                    break;
                case 23: // BEDWARS
                    ByteArrayDataOutput bedwars = ByteStreams.newDataOutput();
                    bedwars.writeUTF("Connect");
                    bedwars.writeUTF("BW-HUB");
                    
                    player.sendPluginMessage(plugin, "BungeeCord", bedwars.toByteArray());
                default:
                    return;
            }
            player.closeInventory();

        // HUBS SELECTOR
        } else if(event.getView().getTitle().equals(messageHandler.replacing(player, "MENUS.HUBS.TITLE")) &&
                  event.getCurrentItem() != null)
        {
            switch(event.getRawSlot()) {
                case 40: // Close Inv
                    break;
                case 21: // HUB-1
                    ByteArrayDataOutput survival = ByteStreams.newDataOutput();
                    survival.writeUTF("Connect");
                    survival.writeUTF("Hub-1");
                    
                    player.sendPluginMessage(plugin, "BungeeCord", survival.toByteArray());
                    break;
                case 23: // HUB-2
                    ByteArrayDataOutput bedwars = ByteStreams.newDataOutput();
                    bedwars.writeUTF("Connect");
                    bedwars.writeUTF("Hub-2");
                    
                    player.sendPluginMessage(plugin, "BungeeCord", bedwars.toByteArray());
                default:
                    return;
            }
            player.closeInventory();
            
        }
    }
    
}
