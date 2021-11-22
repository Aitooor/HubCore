package online.nasgar.hubcore.hubcore.message.provider;

import me.yushust.message.MessageHandler;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.api.user.User;
import online.nasgar.hubcore.hubcore.message.user.liguist.UserLinguist;
import online.nasgar.hubcore.hubcore.message.user.sender.UserMessageSender;
import online.nasgar.hubcore.hubcore.utils.Utils;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.File;

public class MessageHandlerProvider implements Provider<MessageHandler> {

    @Inject private HubCore hubCore;

    @Override
    public MessageHandler get() {
        MessageSourceDecorator messageSourceDecorator =
                MessageSourceDecorator.decorate(
                        BukkitMessageAdapt.newYamlSource(
                                hubCore,
                                new File(
                                        hubCore.getDataFolder(),
                                        "languages"
                                )
                        )
                );
        MessageSource messageSource = messageSourceDecorator
                .addFallbackLanguage("en")
                .addFallbackLanguage("es")
                .get();
        Utils.loadFiles(
                hubCore,
                "languages/lang_en.yml", "languages/lang_es.yml");
        return MessageHandler.of(
            messageSource,
            config -> {
                config.addInterceptor(Utils::ct);

                config.specify(User.class)
                        .setMessageSender(new UserMessageSender())
                        .setLinguist(new UserLinguist());
            }
        );
    }
}
