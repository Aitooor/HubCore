package online.nasgar.hubcore.hubcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.managers.TabManager;
import online.nasgar.hubcore.hubcore.utils.CenteredMessage;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

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
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(
                plugin,
                () -> {
                    Player player = event.getPlayer();
                    if (player == null) {
                        return;
                    }

                    player.setGameMode(GameMode.SURVIVAL);
                    player.getInventory().clear();
                    // Give server selector at first slot
                    player.getInventory().setItem(0, getSelector());
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.getActivePotionEffects().clear();
                    tpSpawn(player);


                    String name = "&f%player_name% ";
                    name = PlaceholderAPI.setPlaceholders(event.getPlayer(), name);
                    String rank = "%vault_prefix% ";
                    rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

                    CenteredMessage.Chat.sendCenteredMessage(player, "");
                    CenteredMessage.Chat.sendCenteredMessage(player, HubCore.getInstance().getConfig().getString("ONJOIN.TITLE"));
                    CenteredMessage.Chat.sendCenteredMessage(player, "");
                    CenteredMessage.Chat.sendCenteredMessage(player, "&a&lIP &7&onasgar.online");
                    CenteredMessage.Chat.sendCenteredMessage(player, "&a&lWEB &7&ohttps://nasgar.online");
                    CenteredMessage.Chat.sendCenteredMessage(player, "&a&lTIENDA &7&ohttps://nasgar.online/shop");
                    CenteredMessage.Chat.sendCenteredMessage(player, "&a&lDISCORD &7&ohttps://ds.nasgar.online");
                    CenteredMessage.Chat.sendCenteredMessage(player, "");
                    CenteredMessage.Chat.sendCenteredMessage(player, plugin.getMessageHandler().replacing(player, "ONJOIN.ONE"));
                    CenteredMessage.Chat.sendCenteredMessage(player, "");
                    CenteredMessage.Chat.sendCenteredMessage(player, rank + name + plugin.getMessageHandler().replacing(player, "ONJOIN.TWO"));

                    TabManager manager = new TabManager(plugin, player);
                    manager.setHeaders(plugin.getMessageHandler().replacingMany(player, "TAB.HEADER", "%bonline%", "%bungee_total%"));
                    manager.setFooters(plugin.getMessageHandler().replacingMany(player, "TAB.FOOTER", "%online%", "%server_online%"));
                    manager.showTab();


                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                }, 2);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            ItemStack currentItem = event.getPlayer().getItemInHand();
            //We can just return this if it is air or null
            if (currentItem == null || currentItem.getType() == Material.AIR) return;
            //We want to make sure that the item HAS item meta and a display name before we try to access it.
            if (!currentItem.hasItemMeta() || (currentItem.hasItemMeta() && !currentItem.getItemMeta().hasDisplayName()))
                return;
            //Now we can actually check the display name. Just for simiplicity let's run it by the getSelector method.
            if (currentItem.getItemMeta().getDisplayName().equals(getSelector().getItemMeta().getDisplayName())) {
                //Let's cancel the event, just because.
                event.setCancelled(true);
                //Let's also update the player's inventory. This isn't really necessary for a compass but it's a good practice to use for more complex things.
                event.getPlayer().updateInventory();
                //And finally, send the message.
                event.getPlayer().sendMessage(Message.translate("&cSorry, this selector is currently in development."));
                //Add menu command
                return;
            }
        }
        //Can't interact with out permission
        if (!event.hasBlock()) return;
        Block block = event.getClickedBlock();
        if (block != null && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    private ItemStack getSelector(){
        ItemStack serverSelector = new ItemStack(Material.COMPASS);
        ItemMeta serverSelectorMeta = serverSelector.getItemMeta();
        serverSelectorMeta.setDisplayName(Message.translate("&aSelector de servidores"));
        ArrayList<String> loreList = new ArrayList<String>();
        loreList.add(ChatColor.GRAY + "» Select The Server You");
        loreList.add(ChatColor.GRAY + "  Wish To Play On");
        serverSelectorMeta.setLore(loreList);
        serverSelector.setItemMeta(serverSelectorMeta);
        return serverSelector;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
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
        World lobbyWorld = Bukkit.getServer().getWorld(config.getString("WORLD.SPAWN"));

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
