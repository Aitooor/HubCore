package com.github.sakiio.listeners;

import com.github.sakiio.manager.PlayerData;
import com.github.sakiio.manager.impl.VaultChatImpl;
import com.github.sakiio.utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class ServerListener implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onSpawnEntity(EntitySpawnEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData();
        String playerTag = playerData.getTags(player);
        String playerColor = playerData.getColorChat(player);
        String playerPrefix = VaultChatImpl.getChat().getPlayerPrefix(player);

        String msg = Color.translate
                (playerTag + " " + playerPrefix + " " +  player.getName() +  "&7: " + playerColor + event.getMessage());
        event.setFormat(msg);
    }
}

