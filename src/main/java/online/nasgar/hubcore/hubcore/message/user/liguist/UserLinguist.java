package online.nasgar.hubcore.hubcore.message.user.liguist;

import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.bukkit.SpigotLinguist;
import me.yushust.message.language.Linguist;
import online.nasgar.hubcore.hubcore.api.user.User;
import org.jetbrains.annotations.Nullable;

public class UserLinguist implements Linguist<User> {

    private final SpigotLinguist spigotLinguist;

    public UserLinguist(){
        this.spigotLinguist = new SpigotLinguist();
    }

    @Override
    public @Nullable String getLanguage(User user) {
        return spigotLinguist.getLanguage(user.getPlayer());
    }
}
