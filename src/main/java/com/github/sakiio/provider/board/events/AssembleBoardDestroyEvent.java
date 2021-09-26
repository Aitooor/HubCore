package com.github.sakiio.provider.board.events;

import org.bukkit.event.*;
import org.bukkit.entity.*;

public class AssembleBoardDestroyEvent extends Event implements Cancellable
{
    public static HandlerList handlerList;
    private Player player;
    private boolean cancelled;
    
    static {
        AssembleBoardDestroyEvent.handlerList = new HandlerList();
    }
    
    public AssembleBoardDestroyEvent(final Player player) {
        this.cancelled = false;
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return AssembleBoardDestroyEvent.handlerList;
    }
    
    public boolean isCancelled() {
        return false;
    }
    
    public void setCancelled(final boolean arg0) {
    }
}
