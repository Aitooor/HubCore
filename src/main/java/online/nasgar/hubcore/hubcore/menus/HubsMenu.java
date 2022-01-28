package online.nasgar.hubcore.hubcore.menus;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.figt.loafmenus.LoafMenu;
import us.figt.loafmenus.LoafMenuItem;
import us.figt.loafmenus.MenuRowSize;
import us.figt.loafmenus.utils.MicroItemBuilder;

import java.util.Collections;

public class HubsMenu extends LoafMenu {

    public HubsMenu(Player player) {
        super(HubCore.getInstance().getLoafMenuRegistrar(), HubCore.getInstance().getMessageHandler().replacing(player, "MENU.HUBS.TITLE"), MenuRowSize.FIVE, player);

        setCloseHandler(closer -> closer.playSound(player.getLocation(), Sound.VILLAGER_HIT, 10, 29));
    }

    @Override
    public LoafMenuItem[] getMenuItems() {
        // newLoafMenuItemArray() is a small shortcut method
        LoafMenuItem[] array = newLoafMenuItemArray();

        LoafMenuItem survival = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name("&b&lTest Item").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            clicker.sendMessage(ChatColor.GREEN + "You " + clickInformation.getType().name() + " clicked slot " + clickInformation.getSlot());
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem microbattles = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name("&b&lTest Item").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            clicker.sendMessage(ChatColor.GREEN + "You " + clickInformation.getType().name() + " clicked slot " + clickInformation.getSlot());
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem bedwars = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name("&b&lTest Item").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            clicker.sendMessage(ChatColor.GREEN + "You " + clickInformation.getType().name() + " clicked slot " + clickInformation.getSlot());
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem practice = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name("&b&lTest Item").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            clicker.sendMessage(ChatColor.GREEN + "You " + clickInformation.getType().name() + " clicked slot " + clickInformation.getSlot());
            // returning true will cancel the click event
            return true;
        });

        // set the items to the respective slots
        array[21] = survival;
        array[23] = bedwars;
        array[25] = microbattles;
        array[27] = practice;

        // replace all air with white stained glass pane
        replaceAll(array, Material.AIR, new ItemStack(Material.STAINED_GLASS_PANE));

        return array;
    }
}
