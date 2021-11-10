package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.LocationUtil;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetSpawnCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if (!player.hasPermission("hubcore.setspawn"))
            return true;

        HubCore.getInstance().getConfig().set("LOCATION.SPAWN", LocationUtil.parseToString(player.getLocation()));
        HubCore.getInstance().saveConfig();
        HubCore.getInstance().reloadConfig();

        player.sendMessage(Message.translate(Message.PREFIX + "&aThe spawn has been set."));
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        return true;
    }
}