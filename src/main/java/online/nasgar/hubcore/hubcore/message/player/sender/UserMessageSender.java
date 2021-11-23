package online.nasgar.hubcore.hubcore.message.player.sender;

import me.yushust.message.send.MessageSender;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.entity.Player;

public class UserMessageSender implements MessageSender<Player> {

    @Override
    public void send(Player user, String mode, String message) {
        Utils.send(
                user,
                "",
                message
        );
    }
}
