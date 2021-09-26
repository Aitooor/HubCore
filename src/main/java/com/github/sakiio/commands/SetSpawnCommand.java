package com.github.sakiio.commands;

import com.github.sakiio.Hub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * Project: Hub
 * Date: 18/08/2021 @ 18:12
 * Class: SetSpawnCommand
 */
public class SetSpawnCommand implements CommandExecutor {
    Hub plugin = Hub.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("hub.manager.setspawn"))
            return true;

        Player player = (Player) sender;

        plugin.getConfig().set("SPAWN.WORLD", player.getWorld().getName());
        plugin.getConfig().set("SPAWN.Y", player.getLocation().getY());
        plugin.getConfig().set("SPAWN.Z", player.getLocation().getZ());
        plugin.getConfig().set("SPAWN.X", player.getLocation().getX());
        try {
            plugin.getConfig().save("config.yml");
            sender.sendMessage("Work");
        } catch (IOException e) {
            sender.sendMessage("no Work");
            e.printStackTrace();
        }
        return false;
    }
}
