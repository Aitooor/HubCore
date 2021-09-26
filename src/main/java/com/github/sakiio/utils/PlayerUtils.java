package com.github.sakiio.utils;

import com.github.sakiio.Hub;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtils {
   public static boolean viewPlayerStatus = true;

    public static void teleportToSpawn(Player player){
        try {
            World w = Bukkit.getServer().getWorld(Hub.getInstance().getConfig().getString("SPAWN.WORLD"));
            double x = Hub.getInstance().getConfig().getDouble("SPAWN.X");
            double y = Hub.getInstance().getConfig().getDouble("SPAWN.Y");
            double z = Hub.getInstance().getConfig().getDouble("SPAWN.Z");
            player.teleport(new Location(w, x, y, z));
        }catch (IllegalArgumentException e){
            Bukkit.getConsoleSender().sendMessage(Color.translate("&cPlease setspawn in Hub."));
        }
    }

    public static void onJoinMessage(Player player){
        player.sendMessage("§7§m-----------------------------------------------------");
        player.sendMessage("");
        player.sendMessage("§fWelcome to the §bHairly Network§f!");
        player.sendMessage("");
        player.sendMessage("§f✦ §bWebsite: §fexample.club");
        player.sendMessage("§f✦ §bStore: §fstore.example.club");
        player.sendMessage("§f✦ §bTeamSpeak: §fts.example.club");
        player.sendMessage("§f✦ §bTwitter: §ftwitter.com/example");
        player.sendMessage("");
        player.sendMessage("§7§m-----------------------------------------------------");
    }

    public static void itemOnJoin(Player player) {
        player.getInventory().setItem(4, serverSelector());
        player.getInventory().setItem(0, cosmeticSelector());
        player.getInventory().setItem(8, viewPlayer());
    }

    public static ItemStack serverSelector() {
        ItemStack serverItem = new ItemMaker(Material.BOOK).setTitle("Selector").build();
        return serverItem;
    }
    public static ItemStack cosmeticSelector() {
        ItemStack cosmeticItem = new ItemMaker(Material.CHEST).setTitle("Cosmetic").build();
        return cosmeticItem;
    }
    public static ItemStack viewPlayer() {
        ItemStack viewItem = new ItemMaker(Material.REDSTONE_TORCH_ON).setTitle("View Player: " + viewPlayerStatus).build();
        return viewItem;
    }
}
