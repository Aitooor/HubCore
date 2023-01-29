package online.nasgar.hubcore.hubcore.managers.basis;

import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import online.nasgar.hubcore.hubcore.HubCore;
import online.nasgar.hubcore.hubcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.List;


public class TablistManager {
    
    private String headers;
    private String footers;
    
    private final HubCore plugin;
    
    private final Player player;
    
    public TablistManager(HubCore plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }
    
    public void showTab() {
        if(headers.isEmpty() && footers.isEmpty()) {
            return;
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if(player == null) {
                        cancel();
                        return;
                    }
                    String header = Utils.translate(PlaceholderAPI.setPlaceholders(player, headers));
                    String footer = Utils.translate(PlaceholderAPI.setPlaceholders(player, footers));
                    PacketPlayOutPlayerListHeaderFooter headerFooter = new PacketPlayOutPlayerListHeaderFooter(
                            IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}"));
                    Field b = headerFooter.getClass().getDeclaredField("b");
                    b.setAccessible(true);
                    b.set(headerFooter, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}"));
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerFooter);
                } catch(IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }, 10, 40);
    }
    
    public void addHeaders(String header) {
        headers += "\n" + header;
    }
    
    public void addFooters(String footer) {
        footers += "\n" + footer;
    }
    
    public void setHeaders(List<String> headers) {
        this.headers = "";
        for(String header : headers) {
            addHeaders(header);
        }
    }
    
    public void setFooters(List<String> footers) {
        this.footers = "";
        for(String footer : footers) {
            addFooters(footer);
        }
    }
    
}