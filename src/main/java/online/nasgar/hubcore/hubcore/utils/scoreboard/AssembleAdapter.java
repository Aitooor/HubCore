package online.nasgar.hubcore.hubcore.utils.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;

public interface AssembleAdapter {

    String getTitle(Player player);

    List<String> getLines(Player player);

    AssembleStyle getStyle();

}
