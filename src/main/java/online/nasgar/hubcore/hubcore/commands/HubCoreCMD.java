package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
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
                Utils.log("&aReloaded completed");
                return true;
            }
            if(args[0].equalsIgnoreCase("setspawn")) {
                Utils.log("You must be a player to perform this command.");
                return true;
            }
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("hubcore.reload")) {
            plugin.getMessageHandler().sendReplacing(sender, "NOPERMISSIONS.PREFIX", "%player%", sender.getName());
            return true;
        }

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getMessageHandler().sendReplacing(sender, "CONFIGRELOAD.MESSAGE", "%player%", sender.getName());
            return true;
        }

        if (!player.hasPermission("hubcore.setspawn")) {
            plugin.getMessageHandler().sendReplacing(sender, "NOPERMISSIONS.PREFIX", "%player%", sender.getName());
            return true;
        }

        if(args[0].equalsIgnoreCase("setspawn")) {
            HubCore.getInstance().getConfig().set("LOCATION.SPAWN", LocationUtil.parseToString(player.getLocation()));
            HubCore.getInstance().saveConfig();
            HubCore.getInstance().reloadConfig();

            player.sendMessage(Utils.ct("&aThe spawn has been set."));
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
            return true;
        }

        return false;
    }

}
