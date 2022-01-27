package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.menus.HubsMenu;
import online.nasgar.hubcore.hubcore.menus.MainMenu;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cYou can't do this by console.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return true;
        }

        switch (args[0].toLowerCase().trim()) {
            case "main":
                new MainMenu(player).open();
                break;
            case "hubs":
                new HubsMenu(player).open();
                break;
            default:
                player.sendMessage(Utils.ct("&b&lNasgar &cInvalid menu example menu"));
                break;
        }
        return true;
    }
}