package com.github.sakiio.manager.impl;

import com.github.sakiio.Hub;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Project: Hub
 * Date: 14/07/2021 @ 10:04
 * Class: VaultChatImpl
 */
public class VaultChatImpl {
    /*
    here i implement vault chat if
    you use don't use it delete and
    replace with your rank api
     */
    private static Chat chat = null;

    public void setupChat() {
        RegisteredServiceProvider<Chat> rsp = Hub.getInstance().getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
    }

    public static Chat getChat() {
        return chat;
    }
}
