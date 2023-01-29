package online.nasgar.hubcore.hubcore.managers.basis.scoreboard.events;

import online.nasgar.hubcore.hubcore.managers.basis.scoreboard.AssembleBoard;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AssembleBoardCreatedEvent extends Event {

    public static HandlerList handlerList = new HandlerList();

    private boolean cancelled = false;
    private final AssembleBoard board;
    
    public AssembleBoardCreatedEvent(AssembleBoard board) {
        this.board = board;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public static void setHandlerList(HandlerList handlerList) {
        AssembleBoardCreatedEvent.handlerList = handlerList;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public AssembleBoard getBoard() {
        return board;
    }
}
