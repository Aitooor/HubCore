package online.nasgar.hubcore.hubcore.listeners.menus;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.managers.basis.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class HubsSelectorListener implements Listener {

    private final HubCore plugin;

    public HubsSelectorListener(HubCore instance) {
        plugin = instance;
    }
    
    MessageHandler messageHandler = MessageManager.getMessageHandler();
    
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // HUBS SELECTOR
        if(event.getView().getTitle().equals(messageHandler.replacing(player, "menus.hubs.titke")) && event.getCurrentItem() != null) {
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
                    break;
                default:
                    return;
            }
            player.closeInventory();
        }
    }
    
}
