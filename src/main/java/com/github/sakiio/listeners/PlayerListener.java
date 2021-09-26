package com.github.sakiio.listeners;

import com.github.sakiio.Hub;
import com.github.sakiio.manager.inventory.SelectorInventory;
import com.github.sakiio.utils.Color;
import com.github.sakiio.utils.PlayerUtils;
import com.github.sakiio.manager.PlayerData;
import com.github.sakiio.manager.model.ColorChat;
import com.github.sakiio.manager.model.Tags;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import static com.github.sakiio.manager.inventory.SelectorInventory.selectorInventory;

/**
 * Project: Train
 * Date: 20/06/2021 @ 19:22
 * Class: PlayerListener
 */
public class PlayerListener implements Listener {
    private final Hub plugin = Hub.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData();

        event.setJoinMessage(null);

        PlayerUtils.onJoinMessage(player);

        PlayerUtils.teleportToSpawn(player);

        PlayerUtils.itemOnJoin(player);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 4));

        player.playSound(player.getLocation(), Sound.NOTE_PLING, 5.0f, 10.0f);

        if (!player.isOp()) player.setGameMode(GameMode.SURVIVAL);

        if (!player.hasPlayedBefore()){
            playerData.createData(player);
            playerData.setColorChat(player, ColorChat.DEFAULT);
            playerData.setTags(player, Tags.PRO);
            playerData.setTokens(player, 400);
            // i make this to test Tags and Color you can change it with enum
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e){
        if(!e.getPlayer().isOp())
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e){
        if(!e.getPlayer().isOp())
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerItemInteraction(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action action = e.getAction();

        if (action.equals(Action.RIGHT_CLICK_AIR)){
            if (player.getInventory().getItemInHand().equals(PlayerUtils.serverSelector())){
                player.openInventory(selectorInventory);
                SelectorInventory.setSelectorInventory();
            }
            if (player.getInventory().getItemInHand().equals(PlayerUtils.cosmeticSelector())){
                player.performCommand("/");
            }
            if (player.getInventory().getItemInHand().equals(PlayerUtils.viewPlayer())){
                if (PlayerUtils.viewPlayerStatus){
                    PlayerUtils.viewPlayerStatus = false;
                    for (Player players : Bukkit.getOnlinePlayers()){
                        player.hidePlayer(players);
                    }
                }else{
                    PlayerUtils.viewPlayerStatus = true;
                    for (Player players : Bukkit.getOnlinePlayers()){
                        player.showPlayer(players);
                    }
                }
            }
        }
    }
}
