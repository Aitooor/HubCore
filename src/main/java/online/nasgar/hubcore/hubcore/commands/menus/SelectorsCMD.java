package online.nasgar.hubcore.hubcore.commands.menus;


import me.clip.placeholderapi.PlaceholderAPI;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectorsCMD implements CommandExecutor {

    private final HubCore plugin;

    public SelectorsCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("You must be a player to perform this command.");
            return true;
        }

        if (args.length == 0) return false;

        Player player = (Player) sender;

        MessageHandler messageHandler = plugin.getMessageHandler();

        FileConfiguration config = HubCore.getInstance().getConfig();

        if (args[0].equalsIgnoreCase("servers")) {
            Inventory inv = Bukkit.createInventory(player, 45, messageHandler.replacing(player, "MENUS.SERVERS.TITLE"));
            // SURVIVAL
            ItemStack survival = new ItemStack(Material.GRASS);
            ItemMeta survivalMeta = survival.getItemMeta();
            survivalMeta.setDisplayName(messageHandler.replacing(player, "MENUS.SERVERS.SURVIVAL.NAME"));

            String survivalCount = "%pb_pc_Survivals%";
            survivalCount = PlaceholderAPI.setPlaceholders(player.getPlayer(), survivalCount);
            int survivalCountInt = Integer.parseInt(survivalCount);

            survivalMeta.setLore(messageHandler.replacingMany(player, "MENUS.SERVERS.SURVIVAL.LORE",
                "%online%", survivalCountInt
            ));
            survival.setItemMeta(survivalMeta);

            inv.setItem(21, survival);

            // BEDWARS
            ItemStack bedwars = new ItemStack(Material.BED);
            ItemMeta bedwarsMeta = bedwars.getItemMeta();
            bedwarsMeta.setDisplayName(messageHandler.replacing(player, "MENUS.SERVERS.WOOL_WARS.NAME"));

            String bwCount = "%pb_pc_BW-All%";
            bwCount = PlaceholderAPI.setPlaceholders(player.getPlayer(), bwCount);
            int bwCountInt = Integer.parseInt(bwCount);

            bedwarsMeta.setLore(messageHandler.replacingMany(player, "MENUS.SERVERS.WOOL_WARS.LORE", "%online%", bwCountInt));
            bedwars.setItemMeta(bedwarsMeta);

            inv.setItem(23, bedwars);

            // CLOSE BUTTON
            ItemStack dyeItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemStack close = new ItemStack(dyeItem);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(messageHandler.replacing(player, "MENUS.CLOSE"));
            close.setItemMeta(closeMeta);

            inv.setItem(40, close);
            //TODO Modify glass filled if possible

            // FRAME STAINED GLASS BLACK
            ItemStack frameBlack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            for(int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,41,42,43,44}) {
                    ItemMeta glassBlackMeta = frameBlack.getItemMeta();
                    glassBlackMeta.setDisplayName(messageHandler.replacing(player, "MENUS.GLASSES"));
                    frameBlack.setItemMeta(glassBlackMeta);

                    inv.setItem(i, frameBlack);
            }

            // FRAME STAINED GLASS BLACK
            ItemStack frameGray = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            for(int i : new int[]{10,11,12,13,14,15,16,19,20,22,24,25,28,29,30,31,32,33,34}) {
                ItemMeta frameGrayMeta = frameGray.getItemMeta();
                frameGrayMeta.setDisplayName(messageHandler.replacing(player, "MENUS.GLASSES"));
                frameGray.setItemMeta(frameGrayMeta);


                inv.setItem(i, frameGray);
            }

            player.openInventory(inv);

            return true;
        }
        else if (args[0].equalsIgnoreCase("hubs")) {
            Inventory inv = Bukkit.createInventory(player, 45, messageHandler.replacing(player, "MENUS.HUBS.TITLE"));

            // HUB-1
            ItemStack hub1 = new ItemStack(Material.valueOf(config.getString("MENU.HUBS.HUB-1.MATERIAL").toUpperCase()));
            ItemMeta hub1Meta = hub1.getItemMeta();
            hub1Meta.setDisplayName(messageHandler.replacing(player, "MENUS.HUBS.ONE.NAME"));
            //TODO Change player count system from Placeholder to Bungee or Redis
            String hub1Count = "%bungee_Hub-1%";
            hub1Count = PlaceholderAPI.setPlaceholders(player, hub1Count);
            int hub1CountInt = Integer.parseInt(hub1Count);

            hub1Meta.setLore(messageHandler.replacingMany(player, "MENUS.HUBS.ONE.LORE", "%online%", hub1CountInt));
            hub1.setItemMeta(hub1Meta);

            inv.setItem(21, hub1);
            //TODO Change player count system from Placeholder to Bungee or Redis

            // HUB-2
            ItemStack hub2 = new ItemStack(Material.valueOf(config.getString("MENU.HUBS.HUB-2.MATERIAL").toUpperCase()));
            ItemMeta hub2meta = hub2.getItemMeta();
            hub2meta.setDisplayName(messageHandler.replacing(player, "MENUS.HUBS.TWO.NAME"));

            String hub2Count = "%bungee_Hub-2%";
            hub2Count = PlaceholderAPI.setPlaceholders(player, hub2Count);
            int hub2CountInt = Integer.parseInt(hub2Count);


            hub2meta.setLore(messageHandler.replacingMany(player, "MENUS.HUBS.TWO.LORE", "%online%", hub2CountInt));
            hub2.setItemMeta(hub2meta);

            inv.setItem(23, hub2);

            // CLOSE BUTTON
            ItemStack dyeItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemStack close = new ItemStack(dyeItem);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(messageHandler.replacing(player, "MENUS.CLOSE"));
            close.setItemMeta(closeMeta);

            inv.setItem(40, close);
            //TODO Modify glass filled if possible

            // FRAME STAINED GLASS BLACK
            ItemStack frameBlack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            for(int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,41,42,43,44}) {
                ItemMeta glassBlackMeta = frameBlack.getItemMeta();
                glassBlackMeta.setDisplayName(messageHandler.replacing(player, "MENUS.GLASSES"));
                frameBlack.setItemMeta(glassBlackMeta);

                inv.setItem(i, frameBlack);
            }

            // FRAME STAINED GLASS BLACK
            ItemStack frameGray = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            for(int i : new int[]{10,11,12,13,14,15,16,19,20,22,24,25,28,29,30,31,32,33,34}) {
                ItemMeta frameGrayMeta = frameGray.getItemMeta();
                frameGrayMeta.setDisplayName(messageHandler.replacing(player, "MENUS.GLASSES"));
                frameGray.setItemMeta(frameGrayMeta);


                inv.setItem(i, frameGray);
            }

            player.openInventory(inv);

            return true;
        }

        return false;
    }

}
