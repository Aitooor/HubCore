package online.nasgar.hubcore.hubcore.message.user.sender;

import me.yushust.message.send.MessageSender;
import online.nasgar.hubcore.hubcore.api.user.User;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.entity.Player;

public class UserMessageSender implements MessageSender<User> {

    @Override
    public void send(User user, String mode, String message) {
        Utils.send(
                user,
                "",
                message
        );
    }
}
