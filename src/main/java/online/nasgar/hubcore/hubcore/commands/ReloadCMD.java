package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static online.nasgar.hubcore.hubcore.utils.Message.PREFIX;

public class ReloadCMD implements CommandExecutor {

    private HubCore plugin;

    public ReloadCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(Message.translate(PREFIX + "&aConfig Reloaded."));
            return true;
        }

        return false;
    }

}
