package online.nasgar.hubcore.hubcore.commands.menus;


import me.clip.placeholderapi.PlaceholderAPI;
import me.yushust.message.MessageHandler;
import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class  MenusCMD implements CommandExecutor {

    private final HubCore plugin;

    public MenusCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length == 0) return false;

        Player player = ((Player) sender).getPlayer();

        MessageHandler messageHandler = HubCore.getInstance().getMessageHandler();

        if (args[0].equalsIgnoreCase("servers")) {
            Inventory inv = Bukkit.createInventory(player, 45, messageHandler.replacing(player, "MENUS.SERVERS.TITLE"));

            // SURVIVAL
            ItemStack survival = new ItemStack(Material.GRASS);
            ItemMeta survivalMeta = survival.getItemMeta();
            survivalMeta.setDisplayName(messageHandler.replacing(player, "MENUS.SERVERS.SURVIVAL.NAME"));

            String survival1 = "%bungee_Survival-1%";
            survival1 = PlaceholderAPI.setPlaceholders(player, survival1);
            String survival2 = "%bungee_Survival-2%";
            survival2 = PlaceholderAPI.setPlaceholders(player, survival2);
            int survivalCount = Integer.parseInt(survival1 + survival2);

            survivalMeta.setLore(messageHandler.replacingMany(player, "MENUS.SERVERS.SURVIVAL.LORE", "%online%", survivalCount));
            survival.setItemMeta(survivalMeta);

            inv.setItem(21, survival);

            // BEDWARS
            ItemStack bedwars = new ItemStack(Material.BED);
            ItemMeta bedwarsMeta = bedwars.getItemMeta();
            bedwarsMeta.setDisplayName("BEDWARS");
            bedwarsMeta.setLore(Arrays.asList("Kills you.", "Prueba"));
            bedwars.setItemMeta(bedwarsMeta);

            inv.setItem(23, bedwars);

            // CLOSE BUTTON
            ItemStack dyeItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemStack close = new ItemStack(dyeItem);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName("Close Inventory");
            close.setItemMeta(closeMeta);

            inv.setItem(40, close);

            // FRAME STAINED GLASS BLACK
            ItemStack frameBlack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            for(int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,41,42,43,44}) {
                    ItemMeta glassBlackMeta = frameBlack.getItemMeta();
                    glassBlackMeta.setDisplayName("Nasgar");
                    frameBlack.setItemMeta(glassBlackMeta);

                    inv.setItem(i, frameBlack);
            }

            // FRAME STAINED GLASS BLACK
            ItemStack frameGray = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            for(int i : new int[]{10,11,12,13,14,15,16,19,20,22,24,25,28,29,30,31,32,33,34}) {
                ItemMeta frameGrayMeta = frameGray.getItemMeta();
                frameGrayMeta.setDisplayName("Nasgar");
                frameGray.setItemMeta(frameGrayMeta);


                inv.setItem(i, frameGray);
            }

            player.openInventory(inv);

            return true;
        }
        else if (args[0].equalsIgnoreCase("hubs")) {
            Inventory inv = Bukkit.createInventory(player, 45, messageHandler.replacing(player, "MENUS.HUBS.TITLE"));

            // HUB-1
            ItemStack hub1 = new ItemStack(Material.COMPASS);
            ItemMeta hub1Meta = hub1.getItemMeta();
            hub1Meta.setDisplayName("HUB-1");
            hub1Meta.setLore(Arrays.asList("Teleport to a random player!", "Prueba"));
            hub1.setItemMeta(hub1Meta);

            inv.setItem(21, hub1);

            // HUB-2
            ItemStack hub2 = new ItemStack(Material.BED);
            ItemMeta hub2Meta = hub2.getItemMeta();
            hub2Meta.setDisplayName("HUB-2");
            hub2Meta.setLore(Arrays.asList("Kills you.", "Prueba"));
            hub2.setItemMeta(hub2Meta);

            inv.setItem(23, hub2);

            // CLOSE BUTTON
            ItemStack dyeItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemStack close = new ItemStack(dyeItem);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName("Close Inventory");
            close.setItemMeta(closeMeta);

            inv.setItem(40, close);

            // FRAME STAINED GLASS BLACK
            ItemStack frameBlack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            for(int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,41,42,43,44}) {
                ItemMeta frameBlackMeta = frameBlack.getItemMeta();
                frameBlackMeta.setDisplayName("Nasgar");
                frameBlack.setItemMeta(frameBlackMeta);

                inv.setItem(i, frameBlack);
            }

            // FRAME STAINED GLASS BLACK
            ItemStack frameGray = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            for(int i : new int[]{10,11,12,13,14,15,16,19,20,22,24,25,28,29,30,31,32,33,34}) {
                ItemMeta frameGrayMeta = frameGray.getItemMeta();
                frameGrayMeta.setDisplayName("Nasgar");
                frameGray.setItemMeta(frameGrayMeta);

                inv.setItem(i, frameGray);
            }

            player.openInventory(inv);

            return true;
        }

        return false;
    }

}
