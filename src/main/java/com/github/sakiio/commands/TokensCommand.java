package com.github.sakiio.commands;

import com.github.sakiio.utils.Color;
import com.github.sakiio.manager.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project: Train
 * Date: 20/06/2021 @ 19:25
 * Class: TokensCommand
 */
public class TokensCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hub.manager.tokens")){
            sender.sendMessage(Color.translate("&cYou don't have perms to use this!"));
            return true;
        }
        Player player = (Player) sender;

        sender.sendMessage(Color.translate("&3You have <tokens> tokens").replace("<tokens>", new PlayerData().getTokens(player)));
        return false;
    }
}
