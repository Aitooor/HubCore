package com.github.sakiio.provider.board.events;

import com.github.sakiio.provider.board.AssembleBoard;
import org.bukkit.event.*;

public class AssembleBoardCreatedEvent extends Event
{
    public static HandlerList handlerList;
    private boolean cancelled;
    private final AssembleBoard board;
    
    static {
        AssembleBoardCreatedEvent.handlerList = new HandlerList();
    }
    
    public AssembleBoardCreatedEvent(final AssembleBoard board) {
        this.cancelled = false;
        this.board = board;
    }
    
    public HandlerList getHandlers() {
        return AssembleBoardCreatedEvent.handlerList;
    }
}
