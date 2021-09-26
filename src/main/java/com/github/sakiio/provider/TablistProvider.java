package com.github.sakiio.provider;

import com.github.sakiio.manager.PlayerData;
import com.github.sakiio.manager.impl.VaultChatImpl;
import com.github.sakiio.provider.tablist.skin.SkinType;
import com.github.sakiio.utils.Color;
import com.github.sakiio.provider.tablist.entry.TabElement;
import com.github.sakiio.provider.tablist.entry.TabElementHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Project: Train
 * Date: 20/06/2021 @ 19:47
 * Class: TablistProvider
 */
public class TablistProvider implements TabElementHandler {
    /**
     * Get the tab element of a player
     *
     * @param player the player
     * @return the element
     */
    @Override
    public TabElement getElement(Player player) {
        final TabElement element = new TabElement();
        final String rank = VaultChatImpl.getChat().getGroupPrefix(player.getWorld(), VaultChatImpl.getChat().getPrimaryGroup(player));

        element.setHeader(Arrays.asList(Color.translate("&3Hairly Network")));
        element.setFooter(Arrays.asList(Color.translate("&3www.example.com")));

        for (int i = 0; i < 80; i++) {

            //Column 0 with row 1-17
            element.add(0, 13, "&3&lUsername");
            element.add(0, 14, player.getName());
            element.add(0, 17,  "&7store.example.club", -1, SkinType.DOLLAR);

            //Column 1 with row 1-17
            element.add(1, 2, "&3&lHairly Network");
            element.add(1, 13, "&3&lRank");
            element.add(1, 14, rank);
            element.add(1, 3, "Online: " + Bukkit.getServer().getOnlinePlayers().size());
            element.add(1, 17, "&7twitter.example.club", -1, SkinType.TWITTER);

            //Column+ 3 with row 1-17
            element.add(2, 13, "&3&lTokens");
            element.add(2, 14, String.valueOf(new PlayerData().getTokens(player)));
            element.add(2, 17, "&7twitter.example.club", -1, SkinType.DISCORD);

        }
        return element;
    }
}
