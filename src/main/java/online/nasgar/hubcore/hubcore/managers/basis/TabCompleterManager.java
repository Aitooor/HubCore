package online.nasgar.hubcore.hubcore.managers.basis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class TabCompleterManager implements TabCompleter {

    private List <String> results = new ArrayList<>();

    @Override
    public List <String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) {
            if (cmd.getLabel().equalsIgnoreCase("hubcore") || cmd.getLabel().equalsIgnoreCase("hc")) {
                results.clear();
                results.add("setspawn");
                results.add("reload");
            }
            else if (cmd.getLabel().equalsIgnoreCase("menus") || cmd.getLabel().equalsIgnoreCase("menu")) {
                results.clear();
                results.add("servers");
                results.add("hubs");
            }
        }
        return sortedResults(args[0]);
    }

    // Sorts possible results to provide true tab auto complete based off of what is already typed.
    public List <String> sortedResults(String arg) {
        final List <String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(arg, results, completions);
        Collections.sort(completions);
        results.clear();
        for (String s : completions) {
            results.add(s);
        }
        return results;
    }

}

