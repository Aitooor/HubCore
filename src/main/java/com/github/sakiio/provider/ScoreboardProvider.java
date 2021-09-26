package com.github.sakiio.provider;

import com.github.sakiio.manager.impl.VaultChatImpl;
import com.github.sakiio.utils.Color;
import com.github.sakiio.provider.board.AssembleAdapter;
import org.bukkit.entity.*;
import java.util.*;

public class ScoreboardProvider implements AssembleAdapter {
    @Override
    public String getTitle(final Player player) {
        return Color.translate("&6&lHairlyGame");
    }

    @Override
    public List<String> getLines(final Player player) {
        final String rank = VaultChatImpl.getChat().getGroupPrefix(player.getWorld(), VaultChatImpl.getChat().getPrimaryGroup(player));
        final List<String> toReturn = new ArrayList<>();

            toReturn.add(Color.translate("&7&m--------------------"));
            toReturn.add(Color.translate(""));
            toReturn.add(Color.translate("&6Check selector to"));
            toReturn.add(Color.translate("&6see servers!"));
            toReturn.add(Color.translate(""));
            toReturn.add(Color.translate("&bRank: " + rank));
            toReturn.add(Color.translate(""));
            toReturn.add(Color.translate("&7&m--------------------"));
            toReturn.add(Color.translate("&6dev.sakio.ga"));
        return toReturn;
    }
}
