package puttingchallenge.model;

/**
 * Interface that represent a game event. Indicates that a colleague-defined event has occurred.
 * When a colleague wants to interact with other colleagues, it calls the Mediator passing the event occured.
 * The Mediator proceeds to notify the other colleagues passing them the event.
 * @param <A>, the Enum type subclass that defines the types of events
 * @param <B>, the type of the object containing details about the event
 */
public interface GameEvent<A extends Enum<A>, B> {

    /**
     * 
     * @return the type of the event
     */
    A getEventType();

    /**
     * 
     * @return the details about the event
     */
    B getDetails();

}
