package com.github.sakiio.manager;

import com.github.sakiio.manager.model.ColorChat;
import com.github.sakiio.manager.model.Tags;
import com.github.sakiio.utils.file.DataFile;
import org.bukkit.entity.Player;

/**
 * Project: Train
 * Date: 18/05/2021 @ 20:16
 * Class: PlayerData
 */
public class PlayerData {
    public void createData(Player player) {
        if (!DataFile.getConfig().getConfigurationSection("PLAYER-DATA").getKeys(false).contains(player.getUniqueId().toString())) {
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".NAME", player.getName());
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".TAGS", Tags.DEFAULT.getPrefix());
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".COLOR", ColorChat.DEFAULT.getColor());
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".TOKENS", 0);
            DataFile.getConfig().saveAll();
        }
    }
        public String getTags(Player player){
            return DataFile.getConfig().getString("PLAYER-DATA." + player.getUniqueId() + ".TAGS");
        }

        public void setTags(Player player, Tags tags) {
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".TAGS", tags.getPrefix());
            DataFile.getConfig().saveAll();
        }

        public String getColorChat(Player player){
            return DataFile.getConfig().getString("PLAYER-DATA." + player.getUniqueId() + ".COLOR");
        }

        public void setColorChat(Player player, ColorChat color) {
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".COLOR", color.getColor());
            DataFile.getConfig().saveAll();
        }

        public String getTokens(Player player){
            return DataFile.getConfig().getString("PLAYER-DATA." + player.getUniqueId() + ".TOKENS");
        }

        public void setTokens(Player player, int tokens){
            DataFile.getConfig().set("PLAYER-DATA." + player.getUniqueId() + ".TOKENS", tokens);
            DataFile.getConfig().saveAll();
    }
}
