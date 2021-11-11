package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static online.nasgar.hubcore.hubcore.utils.Message.PREFIX;

public class SetSpawnCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) 
            return true;
        
        Player player = (Player) sender;
	
	if(!player.isOp()) 
            return true;
        
        HubCore.getInstance().getConfig().set("LOCATION.SPAWN", LocationUtil.parseToString(player.getLocation()));
        HubCore.getInstance().saveConfig();
        HubCore.getInstance().reloadConfig();
        
        player.sendMessage(Message.translate(PREFIX + "&aThe spawn has been set."));
        return true;
    }

}
