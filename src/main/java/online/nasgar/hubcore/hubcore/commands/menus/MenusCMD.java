package online.nasgar.hubcore.hubcore.commands.menus;

import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenusCMD implements CommandExecutor {

    private final HubCore plugin;

    public MenusCMD(HubCore instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length == 0) return false;

        Player player = ((Player) sender).getPlayer();

        if (args[0].equalsIgnoreCase("servers")) {



            return true;
        }

        return false;
    }

}
