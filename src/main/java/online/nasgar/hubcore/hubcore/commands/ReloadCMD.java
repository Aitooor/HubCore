package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCMD implements CommandExecutor {

    private final HubCore plugin;

    public ReloadCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hubcore.reload")) {
            plugin.getMessageHandler().sendReplacing(sender, "NOPERMISSIONS.PREFIX", "%player%", sender.getName());
            return true;
        }
        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getMessageHandler().sendReplacing(sender, "CONFIGRELOAD.MESSAGE", "%player%", sender.getName());
            return true;
        }

        return false;
    }

}
