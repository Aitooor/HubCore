package online.nasgar.hubcore.hubcore.commands.menus;

import com.samjakob.spigui.SGMenu;
import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenusCMD implements CommandExecutor {

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

        if (args[0].equalsIgnoreCase("servers")) {

            // Create a GUI with 3 rows (27 slots)
            SGMenu myAwesomeMenu = HubCore.spiGUI.create(plugin.getMessageHandler().replacing(player, "SERVER_LIST.NAME"), 3);
            myAwesomeMenu.setAutomaticPaginationEnabled(false);

            // Create a button
            SGButton myAwesomeButton = new SGButton(
                    // Includes an ItemBuilder class with chainable methods to easily
                    // create menu items.
                    new ItemBuilder(Material.WOOD).build()
            ).withListener((InventoryClickEvent event) -> {
                // Events are cancelled automatically, unless you turn it off
                // for your plugin or for this inventory.
                event.getWhoClicked().sendMessage("Hello, world!");
            });

            // Add the button to your GUI
            myAwesomeMenu.addButton(myAwesomeButton);

            // Show the GUI
            player.openInventory(myAwesomeMenu.getInventory());

            return true;
        }
        if(args[0].equalsIgnoreCase("hubs")) {

            // Create a GUI with 3 rows (27 slots)
            SGMenu myAwesomeMenu = HubCore.spiGUI.create(plugin.getMessageHandler().replacing(player, "HUBS_LIST.NAME"), 3);
            myAwesomeMenu.setAutomaticPaginationEnabled(false);

            // Create a button
            SGButton myAwesomeButton = new SGButton(
                    // Includes an ItemBuilder class with chainable methods to easily
                    // create menu items.
                    new ItemBuilder(Material.WOOD).build()
            ).withListener((InventoryClickEvent event) -> {
                // Events are cancelled automatically, unless you turn it off
                // for your plugin or for this inventory.
                event.getWhoClicked().sendMessage("Hello, world!");
            });

            // Add the button to your GUI
            myAwesomeMenu.addButton(myAwesomeButton);

            // Show the GUI
            player.openInventory(myAwesomeMenu.getInventory());

            return true;
        }

        return false;
    }

}
