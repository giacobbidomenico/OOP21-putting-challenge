package puttingchallenge.model.events;

import java.util.Optional;

/**
 * Interface that represent a game event. Indicates that a colleague-defined event has occurred.
 * When a colleague wants to interact with other colleagues, it calls the Mediator passing the event occured.
 * The Mediator proceeds to notify the other colleagues passing them the event.
 */
public interface GameEvent {

    /**
     * @return the type of the event
     */
    GameEventType getEventType();

    /**
     * @param <T>
     *          unused
     * @return an empty {@link Optional}
     */
    <T> Optional<T> getDetails();
}
