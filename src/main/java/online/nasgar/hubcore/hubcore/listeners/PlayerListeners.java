package online.nasgar.hubcore.hubcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.CenteredMessage;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListeners implements Listener {

    private final HubCore plugin;

    public PlayerListeners(HubCore instance) {
        plugin = instance;
    }

    FileConfiguration config = HubCore.getInstance().getConfig();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.getFormat();

        Player player = event.getPlayer();

        String rank = "%vault_prefix% &f";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);


        event.setFormat(Message.translate(rank + player.getDisplayName() + "&7: &r" + event.getMessage()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getActivePotionEffects().clear();
        tpSpawn(player);

        String name = "&f%player_name% ";
        name = PlaceholderAPI.setPlaceholders(event.getPlayer(), name);
        String rank = "%vault_prefix% ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        CenteredMessage.Chat.sendCenteredMessageV2(player, "");
        CenteredMessage.Chat.sendCenteredMessageV2(player, HubCore.getInstance().getConfig().getString("ONJOIN.TITLE"));
        CenteredMessage.Chat.sendCenteredMessageV2(player, "");
        CenteredMessage.Chat.sendCenteredMessageV2(player, "&a&lIP &7&onasgar.online");
        CenteredMessage.Chat.sendCenteredMessageV2(player, "&a&lWEB &7&ohttps://nasgar.online");
        CenteredMessage.Chat.sendCenteredMessageV2(player, "&a&lTIENDA &7&ohttps://nasgar.online/shop");
        CenteredMessage.Chat.sendCenteredMessageV2(player, "&a&lDISCORD &7&ohttps://ds.nasgar.online");
        CenteredMessage.Chat.sendCenteredMessageV2(player, "");
        CenteredMessage.Chat.sendCenteredMessageV2(player, plugin.getMessageHandler().get(player, "ONJOIN.ONE"));
        CenteredMessage.Chat.sendCenteredMessageV2(player, "");
        CenteredMessage.Chat.sendCenteredMessageV2(player, rank + name + plugin.getMessageHandler().get(player, "ONJOIN.TWO"));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickItem(PlayerPickupItemEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
            event.getItem().remove();
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.hasBlock()) return;
        Block block = event.getClickedBlock();
        if (block != null && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

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
    public void onRespawn(PlayerRespawnEvent event) {
        if(event.getPlayer() != null)
            tpSpawn(event.getPlayer());
    }

    @EventHandler
    public void onPluginLoad(PluginEnableEvent event) {
        World lobbyWorld = Bukkit.getServer().getWorld(config.getString("LOCATION.WORLD"));

        if(lobbyWorld == null) {
            return;
        }

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
        if(LocationUtil.parseToLocation(config.getString("LOCATION.SPAWN")) == null)
            return;

        player.teleport(LocationUtil.parseToLocation(config.getString("LOCATION.SPAWN")));
    }

}
