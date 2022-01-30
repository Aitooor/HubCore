package online.nasgar.hubcore.hubcore.commands.bungee;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServersCMDs implements CommandExecutor {

    private final HubCore plugin;

    public ServersCMDs(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&aCan't do this by console");
            return false;
        }

        if (!sender.hasPermission("hubcore.servers")) {
            plugin.getMessageHandler().sendReplacing(sender, "NOPERMISSIONS.PREFIX", "%player%", sender.getName());
            return true;
        }
        if (args.length == 0) return false;

        Player player = ((Player) sender).getPlayer();

        if (args[0].equalsIgnoreCase("survival")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("Survival-1");
            } catch (IOException eee) {
                Bukkit.getLogger().info("Now can't you access");
            }
            Bukkit.getPlayer(sender.getName()).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            return true;
        }
        else if(args[0].equalsIgnoreCase("bedwars")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("BW-Hub-1");
            } catch (IOException eee) {
                Bukkit.getLogger().info("Now can't you access");
            }
            Bukkit.getPlayer(sender.getName()).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            return true;
        }
        else if(args[0].equalsIgnoreCase("microbattles")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("MB-Hub-1");
            } catch (IOException eee) {
                Bukkit.getLogger().info("Now can't you access");
            }
            Bukkit.getPlayer(sender.getName()).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            return true;
        }
        else if(args[0].equalsIgnoreCase("practice")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("PR-Hub-1");
            } catch (IOException eee) {
                Bukkit.getLogger().info("Now can't you access");
            }
            Bukkit.getPlayer(sender.getName()).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            return true;
        }
        else if(args[0].equalsIgnoreCase("hub1")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("Hub-1");
            } catch (IOException eee) {
                Bukkit.getLogger().info("Now can't you access");
            }
            Bukkit.getPlayer(sender.getName()).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            return true;
        }
        else if(args[0].equalsIgnoreCase("hub2")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("Hub-2");
            } catch (IOException eee) {
                Bukkit.getLogger().info("Now can't you access");
            }
            Bukkit.getPlayer(sender.getName()).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            return true;
        }

        return false;
    }

}