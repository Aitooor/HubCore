package com.github.sakiio;

import com.github.sakiio.commands.FlyCommand;
import com.github.sakiio.commands.ReloadCommand;
import com.github.sakiio.commands.SetSpawnCommand;
import com.github.sakiio.commands.TokensCommand;
import com.github.sakiio.listeners.ServerListener;
import com.github.sakiio.listeners.WorldListener;
import com.github.sakiio.manager.impl.VaultChatImpl;
import com.github.sakiio.manager.inventory.FlySettingInventory;
import com.github.sakiio.manager.inventory.SelectorInventory;
import com.github.sakiio.provider.ScoreboardProvider;
import com.github.sakiio.listeners.PlayerListener;
import com.github.sakiio.provider.TablistProvider;
import com.github.sakiio.utils.Color;
import com.github.sakiio.provider.board.Assemble;
import com.github.sakiio.provider.board.AssembleStyle;
import com.github.sakiio.provider.tablist.TabAdapter_v1_8_R3;
import com.github.sakiio.provider.tablist.TabHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Map;

public class Hub extends JavaPlugin implements PluginMessageListener {
    @Getter
    private static Hub instance;
    public Map<String, Integer> playerCount;

    public void onEnable(){
        instance = this;

        reloadConfig();
        saveDefaultConfig();

        getCommands();
        getListener();
        getScoreboard();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        Bukkit.getConsoleSender().sendMessage(Color.translate("&7---------------"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&b&lHubCore HairlyGame"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&3Authors: &b"+getDescription().getAuthors()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&3Version: &b"+getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&3Versions: &b" + this.getServer().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&7---------------"));

        new TabHandler(new TabAdapter_v1_8_R3(), new TablistProvider(), this, 250L);
        new VaultChatImpl().setupChat();
    }

    public void onDisable(){}

    public void getCommands(){
        this.getCommand("tokens").setExecutor(new TokensCommand());
        this.getCommand("hreload").setExecutor(new ReloadCommand());
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
    }

    public void getListener(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ServerListener(), getInstance());
        pluginManager.registerEvents(new PlayerListener(), getInstance());
        pluginManager.registerEvents(new WorldListener(), getInstance());
        pluginManager.registerEvents(new FlySettingInventory(), getInstance());
        pluginManager.registerEvents(new SelectorInventory(), getInstance());
    }
    
    public static void getScoreboard() {
        final Assemble assemble = new Assemble(Hub.getInstance(), new ScoreboardProvider());
        assemble.setTicks(2L);
        assemble.setAssembleStyle(AssembleStyle.valueOf(getInstance().getConfig().getString("SCOREBOARD")));
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        try {
            if (message.length == 0) return;
            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subChannel = in.readUTF();
            if (subChannel.equals("PlayerCount")) {
                String server = in.readUTF();
                int playerCount = in.readInt();
                this.playerCount.put(server, playerCount);
            }
        } catch (Exception ignored) {
        }
    }
}
