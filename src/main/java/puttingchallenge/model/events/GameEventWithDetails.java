package puttingchallenge.model.events;

import java.util.Optional;

/**
 * Interface that represent a game event. Indicates that a colleague-defined event has occurred.
 * It Also contains some details about the event.
 * When a colleague wants to interact with other colleagues, it calls the Mediator passing the event occured.
 * The Mediator proceeds to notify the other colleagues passing them the event.
 * @param <B>, the type of the object containing details about the event
 */
public interface GameEventWithDetails<B> extends GameEvent {

    /**
     * @param <T>
     *          the expected type for the detail return
     * @return an optional containing the details about the event, or an empty optional 
     *         if the type <code>T</code> mismatch the type of the detail
     */
    <T> Optional<T> getDetails();

}
