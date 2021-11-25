package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.CenteredMessage;
import online.nasgar.hubcore.hubcore.utils.Message;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCMD implements CommandExecutor {

    private final HubCore plugin;

    public ReloadCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.reloadConfig();
            Utils.log("&aReloaded completed");
            return false;
        }

        Player player = (Player) sender;

        if(!sender.hasPermission("hubcore"))
            return true;

        CenteredMessage.Chat.sendCenteredMessage(player, "");
        CenteredMessage.Chat.sendCenteredMessage(player, "");
        CenteredMessage.Chat.sendCenteredMessage(player, "&b&lHUBCORE &fInfo");
        CenteredMessage.Chat.sendCenteredMessage(player, "");
        CenteredMessage.Chat.sendCenteredMessage(player, plugin.getMessageHandler().replacing(player, "RELOAD.COMMAND"));
        CenteredMessage.Chat.sendCenteredMessage(player, "");
        CenteredMessage.Chat.sendCenteredMessage(player, "");


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
