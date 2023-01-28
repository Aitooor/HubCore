package online.nasgar.hubcore.hubcore.listeners;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemJoinListeners implements Listener {
    
    private final HubCore plugin;

    public ItemJoinListeners(HubCore instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Player player = event.getPlayer();
            if(player == null) {
                return;
            }
            
            // SERVER LIST
            ItemStack servers = new ItemStack(Material.COMPASS);
            ItemMeta serversMeta = servers.getItemMeta();
            serversMeta.setDisplayName(MessageManager.getMessageHandler().replacing(player, "SERVER_LIST.NAME"));
            serversMeta.setLore(MessageManager.getMessageHandler().replacingMany(player, "SERVER_LIST.LORE"));
            servers.setItemMeta(serversMeta);
            event.getPlayer().getInventory().setItem(0, servers);
            
            // HUB LIST
            ItemStack hubs = new ItemStack(Material.BED);
            ItemMeta hubsMeta = hubs.getItemMeta();
            hubsMeta.setDisplayName(MessageManager.getMessageHandler().replacing(player, "HUBS_LIST.NAME"));
            hubsMeta.setLore(MessageManager.getMessageHandler().replacingMany(player, "HUBS_LIST.LORE"));
            hubs.setItemMeta(hubsMeta);
            event.getPlayer().getInventory().setItem(8, hubs);
            
        }, 2);
    }
    
    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ||
           event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            final ItemStack currentItem = event.getPlayer().getItemInHand();
            if(currentItem != null && currentItem.getType() != Material.AIR) {
                if(currentItem.getType() == Material.COMPASS) {
                    player.performCommand("hubcore:menus servers");
                }
                else if(currentItem.getType() == Material.BED) {
                    player.performCommand("hubcore:menus hubs");
                }
            }
            event.setCancelled(true);
        }
    }
    
}
