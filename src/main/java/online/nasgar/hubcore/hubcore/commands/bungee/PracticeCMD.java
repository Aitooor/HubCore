package online.nasgar.hubcore.hubcore.commands.bungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PracticeCMD implements CommandExecutor {

    private final HubCore plugin;

    public PracticeCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cYou can't do this by console.");
            return true;
        }

        if(!sender.hasPermission("hubcore.practice")) {
            Player player = (Player) sender;

            if (args.length < 1) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("PR-Hub");
                player.sendPluginMessage(HubCore.getInstance(), "BungeeCord", out.toByteArray());
                return true;
            }
        }
        return true;
    }
}