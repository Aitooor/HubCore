package online.nasgar.hubcore.hubcore.commands;


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

public class  MenusCMD implements CommandExecutor {

    private final HubCore plugin;

    public MenusCMD(HubCore instance) {
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
            //TODO Change player count system from Placeholder to Bungee or Redis
            String survival1 = "%bungee_Survival-1%";
            survival1 = PlaceholderAPI.setPlaceholders(player, survival1);
            int survival1Int = Integer.parseInt(survival1);
            String survival2 = "%bungee_Survival-2%";
            survival2 = PlaceholderAPI.setPlaceholders(player, survival2);
            int survival2Int = Integer.parseInt(survival2);

            int survivalCountInt = survival1Int + survival2Int;

            survivalMeta.setLore(messageHandler.replacingMany(player, "MENUS.SERVERS.SURVIVAL.LORE", "%online%", survivalCountInt));
            survival.setItemMeta(survivalMeta);

            inv.setItem(21, survival);

            // BEDWARS
            ItemStack bedwars = new ItemStack(Material.BED);
            ItemMeta bedwarsMeta = bedwars.getItemMeta();
            bedwarsMeta.setDisplayName(messageHandler.replacing(player, "MENUS.SERVERS.BEDWARS.NAME"));
            //TODO Change player count system from Placeholder to Bungee or Redis
            String bwHub = "%bungee_BW-Hub%";
            bwHub = PlaceholderAPI.setPlaceholders(player, bwHub);
            int bwHubCount = Integer.parseInt(bwHub);

            // SOLO COUNT
            String bw1 = "%bungee_BW-1%";
            bw1 = PlaceholderAPI.setPlaceholders(player, bw1);
            int bw1Count = Integer.parseInt(bw1);
            String bw2 = "%bungee_BW-2%";
            bw2 = PlaceholderAPI.setPlaceholders(player, bw2);
            int bw2Count = Integer.parseInt(bw2);
            String bw3 = "%bungee_BW-3%";
            bw3 = PlaceholderAPI.setPlaceholders(player, bw3);
            int bw3Count = Integer.parseInt(bw3);
            String bw4 = "%bungee_BW-4%";
            bw4 = PlaceholderAPI.setPlaceholders(player, bw4);
            int bw4Count = Integer.parseInt(bw4);
            String bw5 = "%bungee_BW-5%";
            bw5 = PlaceholderAPI.setPlaceholders(player, bw5);
            int bw5Count = Integer.parseInt(bw5);

            // DOUBLES COUNT
            String bwDoubles1 = "%bungee_BW2-1%";
            bwDoubles1 = PlaceholderAPI.setPlaceholders(player, bwDoubles1);
            int bwDoubles1Count = Integer.parseInt(bwDoubles1);
            String bwDoubles2 = "%bungee_BW2-2%";
            bwDoubles2 = PlaceholderAPI.setPlaceholders(player, bwDoubles2);
            int bwDoubles2Count = Integer.parseInt(bwDoubles2);
            String bwDoubles3 = "%bungee_BW2-3%";
            bwDoubles3 = PlaceholderAPI.setPlaceholders(player, bwDoubles3);
            int bwDoubles3Count = Integer.parseInt(bwDoubles3);
            String bwDoubles4 = "%bungee_BW2-4%";
            bwDoubles4 = PlaceholderAPI.setPlaceholders(player, bwDoubles4);
            int bwDoubles4Count = Integer.parseInt(bwDoubles4);
            String bwDoubles5 = "%bungee_BW2-5%";
            bwDoubles5 = PlaceholderAPI.setPlaceholders(player, bwDoubles5);
            int bwDoubles5Count = Integer.parseInt(bwDoubles5);

            // TRIPLE COUNT
            String bwTriple1 = "%bungee_BW2-1%";
            bwTriple1 = PlaceholderAPI.setPlaceholders(player, bwTriple1);
            int bwTriple1Count = Integer.parseInt(bwTriple1);
            String bwTriple2 = "%bungee_BW2-2%";
            bwTriple2 = PlaceholderAPI.setPlaceholders(player, bwTriple2);
            int bwTriple2Count = Integer.parseInt(bwTriple2);
            String bwTriple3 = "%bungee_BW2-3%";
            bwTriple3 = PlaceholderAPI.setPlaceholders(player, bwTriple3);
            int bwTriple3Count = Integer.parseInt(bwTriple3);
            String bwTriple4 = "%bungee_BW2-4%";
            bwTriple4 = PlaceholderAPI.setPlaceholders(player, bwTriple4);
            int bwTriple4Count = Integer.parseInt(bwTriple4);
            String bwTriple5 = "%bungee_BW2-5%";
            bwTriple5 = PlaceholderAPI.setPlaceholders(player, bwTriple5);
            int bwTriple5Count = Integer.parseInt(bwTriple5);

            // SQUAD COUNT
            String bwSquad1 = "%bungee_BW2-1%";
            bwSquad1 = PlaceholderAPI.setPlaceholders(player, bwSquad1);
            int bwSquad1Count = Integer.parseInt(bwSquad1);
            String bwSquad2 = "%bungee_BW2-2%";
            bwSquad2 = PlaceholderAPI.setPlaceholders(player, bwSquad2);
            int bwSquad2Count = Integer.parseInt(bwSquad2);
            String bwSquad3 = "%bungee_BW2-3%";
            bwSquad3 = PlaceholderAPI.setPlaceholders(player, bwSquad3);
            int bwSquad3Count = Integer.parseInt(bwSquad3);
            String bwSquad4 = "%bungee_BW2-4%";
            bwSquad4 = PlaceholderAPI.setPlaceholders(player, bwSquad4);
            int bwSquad4Count = Integer.parseInt(bwSquad4);
            String bwSquad5 = "%bungee_BW2-5%";
            bwSquad5 = PlaceholderAPI.setPlaceholders(player, bwSquad5);
            int bwSquad5Count = Integer.parseInt(bwSquad5);

            int bwSolo = bw1Count + bw2Count + bw3Count + bw4Count + bw5Count;
            int bwDoubles = bwDoubles1Count + bwDoubles2Count + bwDoubles3Count + bwDoubles4Count + bwDoubles5Count;
            int bwTriple = bwTriple1Count + bwTriple2Count + bwTriple3Count + bwTriple4Count + bwTriple5Count;
            int bwSquad = bwSquad1Count + bwSquad2Count + bwSquad3Count + bwSquad4Count + bwSquad5Count;

            int bedwarsCountInt = bwHubCount + bwSolo + bwDoubles + bwTriple + bwSquad;

            bedwarsMeta.setLore(messageHandler.replacingMany(player, "MENUS.SERVERS.BEDWARS.LORE", "%online%", bedwarsCountInt));
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
