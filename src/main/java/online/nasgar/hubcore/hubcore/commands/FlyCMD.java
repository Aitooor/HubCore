package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class FlyCMD implements CommandExecutor {

    private final HubCore plugin;

    public FlyCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("You must be a player to perform this command.");
            return false;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("hubcore.fly")) {
            plugin.getMessageHandler().sendReplacing(sender, "NOPERMISSIONS.PREFIX");
            return true;
        }

        if (args.length == 0) {
            toggleFly(sender, p.getName());
        }
        else if (args.length == 1) {
            if (!sender.hasPermission("hubcore.fly.other")) {
                plugin.getMessageHandler().sendReplacing(sender, "NOPERMISSIONS.PREFIX");
                return true;
            }
            if (checkOnline(args[0])) {
                toggleFly(sender, args[0]);
            } else {
                plugin.getMessageHandler().sendReplacing(sender, "NOPLAYERFOUND.PREFIX");
                return true;
            }
        }

        return false;
    }

    public String findPlayer(String s) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getDisplayName().toLowerCase().contains(s.toLowerCase())) return p.getName();
        }
        return "PlayerNotFound";
    }

    public boolean checkOnline(String s) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getDisplayName().toLowerCase().contains(s.toLowerCase())) return true;
        }
        return false;
    }

    public Set<Player> flying = new HashSet<>();

    public void toggleFly(CommandSender sender, String name) {
        Player p = Bukkit.getPlayer(findPlayer(name));
        if (!p.isOnline()) {
            plugin.getMessageHandler().sendReplacing(sender, "NO_PLAYER_ONLINE.PREFIX");
            return;
        }
        if (flying.contains(p)) {
            p.sendMessage("Your fly mode was toggled " + "off" + ".");
            flying.remove(p);
            p.setAllowFlight(false);
            sender.sendMessage("Fly mode " + "disabled" + " for " + p.getDisplayName() + ".");
        } else {
            p.sendMessage("Your fly mode was toggled " + "on" + ".");
            flying.add(p);
            p.setAllowFlight(true);
            sender.sendMessage("Fly mode " + "enabled" + " for " + p.getDisplayName() + ".");
        }
    }
    
}