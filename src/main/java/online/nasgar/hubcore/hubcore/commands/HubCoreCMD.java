package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.managers.basis.MessageManager;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCoreCMD implements CommandExecutor {

    private final HubCore plugin;

    public HubCoreCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) return false;
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                MessageManager.getMessageHandler().getSource().load("en");
                MessageManager.getMessageHandler().getSource().load("es");
                Utils.log("&aReloaded completed");
                return true;
            }
            if(args[0].equalsIgnoreCase("setspawn")) {
                Utils.log("You must be a player to perform this command.");
                return true;
            }
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("hubcore.reload")) {
            MessageManager.getMessageHandler().sendReplacing(sender, "basis.no_permissions", "%player%", sender.getName());
            return true;
        }

        if (args.length == 0) return false;

        if (!player.hasPermission("hubcore.admin")) {
            MessageManager.getMessageHandler().sendReplacing(sender, "basis.no_permissions", "%player%", sender.getName());
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            MessageManager.getMessageHandler().getSource().load("en");
            MessageManager.getMessageHandler().getSource().load("es");
            MessageManager.getMessageHandler().sendReplacing(sender, "basis.reload_config", "%player%", sender.getName());
            return true;
        }

        if(args[0].equalsIgnoreCase("setspawn")) {
            HubCore.getInstance().getConfig().set("locations.spawn", LocationUtil.parseToString(player.getLocation()));
            HubCore.getInstance().saveConfig();
            HubCore.getInstance().reloadConfig();

            MessageManager.getMessageHandler().send(player, "basis.set_spawn");
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
            return true;
        }

        return false;
    }

}
