package com.github.sakiio.manager.inventory;

import com.github.sakiio.Hub;
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

/**
 * Project: Hub
 * Date: 08/07/2021 @ 23:23
 * Class: SelectorInventory
 */
public class SelectorInventory implements Listener {
    public static Inventory selectorInventory = Bukkit.createInventory(null, 1*9, "Selector");

    public static void setSelectorInventory(){
        selectorInventory.setItem(4, new ItemMaker(Material.BOOK_AND_QUILL).setTitle("&3Sumo").build());
        InventoryUtils.getFill(selectorInventory);
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

        if (!topInventory.equals(selectorInventory))
            return;

        if (topInventory.equals(clickedInventory)) {
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();
            if (item == null || item.getType() == Material.AIR || item.getType() == Material.STAINED_GLASS_PANE) {
                return;
            }
        }
        if (event.getSlot() == 4) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("Sumo");
            player.sendPluginMessage((Hub.getInstance()), "BungeeCord", out.toByteArray());
            player.sendMessage(Color.translate("&aConnecting to &osumo"));
        }
    }
}
