package puttingchallenge.model.events;

/**
 * Interface that represent a game event. Indicates that a colleague-defined event has occurred.
 * When a colleague wants to interact with other colleagues, it calls the Mediator passing the event occured.
 * The Mediator proceeds to notify the other colleagues passing them the event.
 * @param <A>, the Enum type subclass that defines the types of events
 */
public interface GameEvent<A extends Enum<A>> {

    /**
     * @return the type of the event
     */
    A getEventType();

}
