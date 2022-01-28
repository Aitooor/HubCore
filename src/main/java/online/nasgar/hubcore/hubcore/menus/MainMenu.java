package online.nasgar.hubcore.hubcore.menus;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.figt.loafmenus.*;
import us.figt.loafmenus.utils.MicroItemBuilder;

import java.util.Collections;

public class MainMenu extends LoafMenu {

    public MainMenu(Player player) {
        super(HubCore.getInstance().getLoafMenuRegistrar(), HubCore.getInstance().getMessageHandler().replacing(player, "MENU.MAIN.TITLE"), MenuRowSize.FIVE, player);

        setCloseHandler(closer -> closer.playSound(player.getLocation(), Sound.VILLAGER_HIT, 10, 29));
    }

    @Override
    public LoafMenuItem[] getMenuItems() {
        // newLoafMenuItemArray() is a small shortcut method
        LoafMenuItem[] array = newLoafMenuItemArray();

        LoafMenuItem survival = new LoafMenuItem(new MicroItemBuilder(Material.GRASS).name("&aSURVIVAL &7&o(&f&o1.18&7&o)").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            Bukkit.dispatchCommand(clicker, "hubcore:servers survival");
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem microbattles = new LoafMenuItem(new MicroItemBuilder(Material.WOOD_SWORD).name("&aMICRO BATTLES &7&o(&f&o1.7&7&o-&f&o1.8&7&o)").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            Bukkit.dispatchCommand(clicker, "hubcore:servers microbattles");
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem bedwars = new LoafMenuItem(new MicroItemBuilder(Material.BED).name("&aBEDWARS &7&o(&f&o1.7&7&o-&f&o1.8&7&o)").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            Bukkit.dispatchCommand(clicker, "hubcore:servers bedwars");
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem practice = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name("&aPRACTICE &7&o(&f&o1.7&7&o-&f&o1.8&7&o)").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            Bukkit.dispatchCommand(clicker, "hubcore:servers practice");
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem profile = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name(HubCore.getInstance().getMessageHandler().getMessage("PROFILE.TITLE")).lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            Bukkit.dispatchCommand(clicker, "profile");
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem wiki = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name(HubCore.getInstance().getMessageHandler().getMessage("HELP.TITLE")).lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            Bukkit.dispatchCommand(clicker, "help");
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem discord = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name("&aDISCORD").lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            clicker.sendMessage(Utils.ct(HubCore.getInstance().getConfig().getString( "DISCORD.URL")));
            clicker.closeInventory();
            // returning true will cancel the click event
            return true;
        });

        LoafMenuItem shop = new LoafMenuItem(new MicroItemBuilder(Material.DIAMOND_SWORD).name(HubCore.getInstance().getMessageHandler().getMessage("SHOP.TITLE")).lore(Collections.singletonList("&7Click me!")).build(), (clicker, clickInformation) -> {
            clicker.sendMessage(Utils.ct(HubCore.getInstance().getMessageHandler().replacing(clicker, "SHOP.PREFIX") + HubCore.getInstance().getConfig().getString( "SHOP.URL")));
            clicker.closeInventory();
            // returning true will cancel the click event
            return true;
        });

        // set the items to the respective slots
        array[21] = survival;
        array[25] = bedwars;
        array[14] = microbattles;
        array[32] = practice;
        array[18] = profile;
        array[19] = wiki;
        array[9] = discord;
        array[27] = shop;
        // replace all air with white stained glass pane
        replaceAll(array, Material.AIR, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));

        return array;
    }
}