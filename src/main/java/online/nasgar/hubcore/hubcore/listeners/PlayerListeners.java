package online.nasgar.hubcore.hubcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.adapter.TablistAdapter;
import online.nasgar.hubcore.hubcore.managers.basis.MessageManager;
import online.nasgar.hubcore.hubcore.utils.centermsg.CenteredMessage;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListeners implements Listener {
    
    private final HubCore plugin;
    private final FileConfiguration config;
    private final MessageHandler messageManager = MessageManager.getMessageHandler();

    public PlayerListeners(HubCore instance, FileConfiguration config) {
        this.config = config;
        plugin = instance;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String messages = messageManager.replacing(player,"join_message");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(player == null) {
                return;
            }

            for(String message : messages.split("\n")) {
                CenteredMessage.sendCenteredMessage(player,
                    PlaceholderAPI.setPlaceholders(
                            player, message.replace("%rank%", PlaceholderAPI.setPlaceholders(player, "%vault_prefix%"))
                    )
                );
            }

            new TablistAdapter(plugin).loadTablist(player);
            
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
        }, 2);
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getActivePotionEffects().clear();
        
        tpSpawn(player);
        
        event.setJoinMessage(null);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) { event.setQuitMessage(null); }
    
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        if(player.getLocation().getBlockY() < 0) {
            tpSpawn(player);
        }
    }
    
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().hasPermission("hubcore.admin")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(!event.getPlayer().hasPermission("hubcore.admin")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(!event.getPlayer().hasPermission("hubcore.admin")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerPickItem(PlayerPickupItemEvent event) {
        if(!event.getPlayer().hasPermission("hubcore.admin")) {
            event.setCancelled(true);
            event.getItem().remove();
        }
    }
    
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getWhoClicked().hasPermission("hubcore.admin")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }
    
    @EventHandler
    public void onWeather(WeatherChangeEvent event) { event.setCancelled(true); }
    
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.setDroppedExp(0);
        event.getDrops().clear();
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if(event.getPlayer() != null) {
            if(config.getString("locations.spawn") == null) return;
            tpSpawn(event.getPlayer());
        }
    }
    
    @EventHandler
    public void onPluginLoad(PluginEnableEvent event) {
        
        if(config.getString("locations.spawn") == null) return;
        
        World lobbyWorld = Bukkit.getServer().getWorld(config.getString("locations.spawn").split(", ")[5]);
        
        lobbyWorld.setGameRuleValue("doDaylightCycle", "false");
        lobbyWorld.setTime(3600);
        lobbyWorld.setStorm(false);
        lobbyWorld.setWeatherDuration(0);
        lobbyWorld.setAnimalSpawnLimit(0);
        lobbyWorld.setAmbientSpawnLimit(0);
        lobbyWorld.setMonsterSpawnLimit(0);
        lobbyWorld.setWaterAnimalSpawnLimit(0);
    }
    
    private void tpSpawn(Player player) {
        if(config.getString("locations.spawn") == null) return;

        player.teleport(LocationUtil.parseToLocation(config.getString("locations.spawn")));
    }

}
