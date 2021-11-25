package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetSpawnCMD implements CommandExecutor {

    private final HubCore plugin;

    public SetSpawnCMD(HubCore plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cYou can't do this by console.");
            return true;
        }
        
        Player player = (Player) sender;
	
	if(!player.hasPermission("hubcore.setspawn"))
            return true;
        
        HubCore.getInstance().getConfig().set("WORLD.SPAWN", LocationUtil.parseToString(player.getLocation()));
        HubCore.getInstance().saveConfig();
        HubCore.getInstance().reloadConfig();

        plugin.getMessageHandler().sendReplacing(sender, "SETSPAWN.MESSAGE", "%player%", sender.getName());
        return true;
    }

}
