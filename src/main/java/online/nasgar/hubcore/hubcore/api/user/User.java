package online.nasgar.hubcore.hubcore.api.user;

import online.nasgar.hubcore.hubcore.api.repository.key.annotation.InstanceDeserializer;
import online.nasgar.hubcore.hubcore.api.repository.key.annotation.RepositoryKey;
import online.nasgar.hubcore.hubcore.api.serializer.Serializable;
import online.nasgar.hubcore.hubcore.api.user.impl.UserImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;


/**
 * Represents the object of the player
 * to get Statistics and another data.
 *
 * @see Serializable
 */
@RepositoryKey("users")
@InstanceDeserializer(UserImpl.class)
public interface User extends Serializable {

    @Nullable Player getPlayer();

    void setPlayer(Player player);
}
