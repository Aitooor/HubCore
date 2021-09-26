package com.github.sakiio.commands;

import com.github.sakiio.Hub;
import com.github.sakiio.utils.Color;
import com.github.sakiio.utils.file.DataFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Project: Hub
 * Date: 07/08/2021 @ 23:27
 * Class: ReloadCommand
 */
public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!sender.hasPermission("hub.manager.reload")){
            sender.sendMessage(Color.translate("&cYou don't have perms to use this!"));
            return true;
        }

        DataFile.getConfig().reload();
        Hub.getInstance().reloadConfig();

        sender.sendMessage(Color.translate("&aAll Config has been reloaded!"));

        return false;
    }
}
