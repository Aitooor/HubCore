package online.nasgar.hubcore.hubcore.utils.scoreboard.events;

import lombok.Getter;
import lombok.Setter;
import online.nasgar.hubcore.hubcore.utils.scoreboard.AssembleBoard;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class AssembleBoardCreatedEvent extends Event {

    @Getter
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
}
