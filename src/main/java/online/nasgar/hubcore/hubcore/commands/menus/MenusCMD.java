package online.nasgar.hubcore.hubcore.commands.menus;


import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.yushust.message.MessageHandler;
import net.kyori.adventure.text.Component;
import online.nasgar.hubcore.hubcore.HubCore;
import org.bukkit.Material;
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

        MessageHandler messageHandler = HubCore.getInstance().getMessageHandler();

        if (args[0].equalsIgnoreCase("servers")) {
            Gui gui = Gui.gui()
                    .title(Component.text(messageHandler.replacing(player, "MENUS.SERVERS.TITLE")))
                    .rows(5)
                    .create();

            gui.open(player);

            return true;
        }

        return false;
    }

}
