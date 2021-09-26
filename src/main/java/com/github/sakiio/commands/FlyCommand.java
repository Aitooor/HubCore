package com.github.sakiio.commands;

import com.github.sakiio.manager.inventory.FlySettingInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project: Hub
 * Date: 25/09/2021 @ 12:42
 * Class: FlyCommand
 */
public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("fly.command"))
            return true;

        Player player = (Player) commandSender;

        FlySettingInventory.setFlySelector();
        player.openInventory(FlySettingInventory.flySelectorInventory);
        return false;
    }
}
