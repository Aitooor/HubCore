package com.github.sakiio.manager.inventory;

import com.github.sakiio.commands.FlyCommand;
import com.github.sakiio.utils.Color;
import com.github.sakiio.utils.InventoryUtils;
import com.github.sakiio.utils.ItemMaker;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.github.sakiio.manager.inventory.SelectorInventory.selectorInventory;

/**
 * Project: Hub
 * Date: 20/09/2021 @ 17:09
 * Class: FlySettingInventory
 */
public class FlySettingInventory implements Listener {
    public static boolean flyStatus = false;
    public static Inventory flySelectorInventory = Bukkit.createInventory(null, 1*9, "Fly Selector");

    public static void setFlySelector(){
        flySelectorInventory.setItem(4, new ItemMaker(Material.BOOK_AND_QUILL).setTitle("&3Fly")
                .setLore("Status: " + flyStatus).build());
        InventoryUtils.getFill(flySelectorInventory);
    }

    @EventHandler
    public void onPlayerInventoryInteraction(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory topInventory = event.getView().getTopInventory();
        Inventory clickedInventory = event.getClickedInventory();

        if (player.getGameMode().equals(GameMode.CREATIVE) || player.isOp())
            return;

        if (player.getGameMode().equals(GameMode.SURVIVAL))
            event.setCancelled(true);

        if (!topInventory.equals(flySelectorInventory))
            return;

        if (topInventory.equals(clickedInventory)) {
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();
            if (item == null || item.getType() == Material.AIR || item.getType() == Material.STAINED_GLASS_PANE) {
                return;
            }
        }
        if (event.getSlot() == 4) {
            if (flyStatus) {
                flyStatus = false;
                player.setAllowFlight(false);
                player.setFlying(false);
            } else if (!(flyStatus)) {
                flyStatus = true;
                player.setAllowFlight(true);
                player.setFlying(true);
            }
        }
    }
}
