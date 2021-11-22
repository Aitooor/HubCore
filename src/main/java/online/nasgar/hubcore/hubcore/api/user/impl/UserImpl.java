package online.nasgar.hubcore.hubcore.api.user.impl;

import com.google.gson.annotations.Expose;
import online.nasgar.hubcore.hubcore.api.user.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class UserImpl implements User {

    private Player player;
    @Expose
    private final String id;
    private final String name;

    public UserImpl(Player player){
        this.player = player;
        this.name = player.getName() != null ? player.getName() : "unknown";
        this.id = player.getUniqueId() != null ? player.getUniqueId().toString() : "unknown";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public @Nullable Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
