package online.nasgar.hubcore.hubcore.commands;

import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.CenteredMessage;
import online.nasgar.hubcore.hubcore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static online.nasgar.hubcore.hubcore.utils.Message.PREFIX;

public class FlyCMD implements CommandExecutor {

    private HubCore plugin;

    public FlyCMD(HubCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length == 0) {
                if (!sender.hasPermission("hubcore.fly")) {
                    sender.sendMessage(Message.NO_PERMISSION);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Message.NO_CONSOLE);
                    return true;
                }
                Player p = (Player) sender;
                toggleFly(sender, p.getName());
            } else if (args.length == 1) {
                if (!sender.hasPermission("hubcore.fly.others")) {
                    sender.sendMessage(Message.NO_PERMISSION);
                    return true;
                }
                if (checkOnline(args[0])) {
                    toggleFly(sender, args[0]);
                } else {
                    sender.sendMessage(Message.NO_PLAYER_FOUND);
                    return true;
                }
            } else if (args.length == 2) {
                if (!sender.hasPermission("hubcore.fly.")) {
                    sender.sendMessage(Message.NO_PERMISSION);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Message.NO_CONSOLE);
                    return true;
                }
                if (args[0].equalsIgnoreCase("speed")) {
                    Player p = (Player) sender;
                    setSpeed(sender, p, args[1]);
                } else {
                    sender.sendMessage(Message.SYNTAX_ERROR);
                    return true;
                }
            } else if (args.length == 3) {
                if (!sender.hasPermission("hubcore.fly.speed.others")) {
                    sender.sendMessage(Message.NO_PERMISSION);
                    return true;
                }
                Player p;
                if (checkOnline(args[0])) {
                    if (args[0].equalsIgnoreCase("speed")) {
                        if (checkOnline(args[1])) {
                            p = Bukkit.getPlayer(findPlayer(args[1]));
                            setSpeed(sender, p, args[2]);
                        } else {
                            sender.sendMessage(Message.NO_PLAYER_FOUND);
                            return true;
                        }
                    } else {
                        sender.sendMessage(Message.SYNTAX_ERROR);
                        return true;
                    }
                } else {
                    sender.sendMessage(Message.NO_PLAYER_FOUND);
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("flytimer")) {
            if (!sender.hasPermission("hubcore.fly.timer")) {
                sender.sendMessage(Message.NO_PERMISSION);
                return true;
            }
            if (args.length != 2) {
                Player player = (Player) sender;
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&f");
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&f");
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&b&lNASGAR");
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&f");
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&a/flytimer &f<name> <time + s|m|h> &7(ie. /flytimer notch 10m");
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&f");
                CenteredMessage.Chat.sendCenteredMessageV2(player, "&f");
            }

            final Player p;
            if (checkOnline(args[0])) {
                p = Bukkit.getPlayer(findPlayer(args[0]));
            } else {
                sender.sendMessage(Message.NO_PLAYER_FOUND);
                return true;
            }

            int time = 0;
            if (args[1].endsWith("s")) {
                time = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 20;
            } else if (args[1].endsWith("m")) {
                time = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 20 * 60;
            } else if (args[1].endsWith("h")) {
                time = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 20 * 60 * 60;
            } else {
                p.sendMessage(Message.translate(PREFIX + "&fTo specify a time, do a number followed by &a(Number)s|m|h &7(ie. 10m or 1h or 53s)"));
                return true;
            }

            p.setAllowFlight(true);
            sender.sendMessage(Message.translate(PREFIX + ChatColor.GREEN + p.getDisplayName() + " &fis allowed to fly for &a" + args[1]));
            p.sendMessage(Message.translate(PREFIX + "&fYou are allowed to fly for &a" + args[1]));
            flying.add(p);

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    p.setAllowFlight(false);
                    flying.remove(p);
                    p.sendMessage(Message.translate(PREFIX + "&cYour fly limit has ended."));
                }
            }, (long) time);
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
            sender.sendMessage(Message.PLAYER_NO_ONLINE);
            return;
        }
        if (flying.contains(p)) {
            p.sendMessage(Message.translate(PREFIX + "&fYour fly mode was toggled &coff"));
            flying.remove(p);
            p.setAllowFlight(false);
            sender.sendMessage(Message.translate(PREFIX + "&fFly mode &cdisabled &ffor &a" + p.getDisplayName()));
        } else {
            p.sendMessage(Message.translate(PREFIX + "&fYour fly mode was toggled &aon"));
            flying.add(p);
            p.setAllowFlight(true);
            sender.sendMessage(Message.translate(PREFIX + "&fFly mode &aenabled &ffor &a" + p.getDisplayName()));
        }
    }

    public void setSpeed(CommandSender sender, Player p, String speed) {
        int integer = Integer.parseInt(speed);
        if (integer > 99) {
            sender.sendMessage(Message.translate(PREFIX + "&cPlease enter a number less than 100%."));
            return;
        }

        float f = (float) (integer * .01);
        p.setFlySpeed(f);

        sender.sendMessage(Message.translate(PREFIX + "&fSet &f" + p.getDisplayName() + "&f's fly speed to &a" + speed + "%"));
        p.sendMessage(Message.translate(PREFIX + "&fYour fly speed was set to &a" + speed + "%"));
    }

}