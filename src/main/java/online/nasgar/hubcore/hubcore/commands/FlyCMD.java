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

    public FlyCMD(HubCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cYou can't do this by console.");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length == 0) {
                if (!sender.hasPermission("hubcore.fly")) {
                    plugin.getMessageHandler().sendReplacing(player, "NOPERMISSIONS.PREFIX", "%player%", player.getName());
                    return true;
                }
                toggleFly(sender, player.getName());
            } else if (args.length == 1) {
                if (!sender.hasPermission("hubcore.fly.others")) {
                    plugin.getMessageHandler().sendReplacing(player, "NOPERMISSIONS.PREFIX", "%player%", player.getName());
                    return true;
                }
                if (checkOnline(args[0])) {
                    toggleFly(sender, args[0]);
                } else {
                    plugin.getMessageHandler().sendReplacing(player, "NOPLAYERFOUND.PREFIX", "%player%", player.getName());
                    return true;
                }
            } else if (args.length == 2) {
                if (!sender.hasPermission("hubcore.fly.")) {
                    plugin.getMessageHandler().sendReplacing(player, "NOPERMISSIONS.PREFIX", "%player%", player.getName());
                    return true;
                }
            }
        }
        return true;
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
            plugin.getMessageHandler().sendReplacing(sender, "NO_PLAYER_ONLINE.PREFIX", "%player%", sender.getName());
            return;
        }
        if (flying.contains(p)) {
            plugin.getMessageHandler().sendReplacing(sender, "FLY.TOGGLE_OFF", "%player%", sender.getName());
            flying.remove(p);
            p.setAllowFlight(false);
            plugin.getMessageHandler().sendReplacing(sender, "FLY.DISABLED_TO", "%player%", sender.getName());
        } else {
            plugin.getMessageHandler().sendReplacing(sender, "FLY.TOGGLE_ON", "%player%", sender.getName());
            flying.add(p);
            p.setAllowFlight(true);
            plugin.getMessageHandler().sendReplacing(sender, "FLY.ENABLED_TO", "%player%", sender.getName());
        }
    }
}