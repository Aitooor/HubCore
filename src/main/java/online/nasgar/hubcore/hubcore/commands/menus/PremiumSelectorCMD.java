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

public class PremiumSelectorCMD implements CommandExecutor {

    private final HubCore plugin;

    public PremiumSelectorCMD(HubCore instance) {
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

        if (args[0].equalsIgnoreCase("menu")) {
            Inventory inv = Bukkit.createInventory(player, 45, messageHandler.replacing(player, "PREMIUM.TITLE"));
            // PREMIUM ENABLE
            ItemStack survival = new ItemStack(Material.GRASS);
            ItemMeta survivalMeta = survival.getItemMeta();
            survivalMeta.setDisplayName(messageHandler.replacing(player, "PREMIUM.ENABLE.NAME"));

            survivalMeta.setLore(messageHandler.replacingMany(player, "PREMIUM.ENABLE.LORE"));
            survival.setItemMeta(survivalMeta);

            inv.setItem(21, survival);

            // PREMIUM CANCEL
            ItemStack bedwars = new ItemStack(Material.BED);
            ItemMeta bedwarsMeta = bedwars.getItemMeta();
            bedwarsMeta.setDisplayName(messageHandler.replacing(player, "PREMIUM.CANCEL.NAME"));

            bedwarsMeta.setLore(messageHandler.replacingMany(player, "PREMIUM.CANCEL.LORE"));
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
        return false;
    }

}
