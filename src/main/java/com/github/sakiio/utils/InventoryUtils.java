package com.github.sakiio.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Project: Train
 * Date: 18/05/2021 @ 21:01
 * Class: InventoryUtils
 */
public class InventoryUtils {
    public static void getFill(Inventory inv) {
        for(int i = 0; i < inv.getSize(); ++i) {
            if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                inv.setItem(i, new ItemMaker(Material.STAINED_GLASS_PANE).setTitle("store.hairly.game").build());
            }
        }
    }
}
