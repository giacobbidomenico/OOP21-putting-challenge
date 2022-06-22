package puttingchallenge.model.events;

/**
 * Implementation of {@link GameEventWithDetails} interface.
 * @param <B>, the type of the object containing details about the event
 */
public class GameEventWithDetailsImpl<B> extends GameEventImpl {

    private final B details;

    /**
     * Build a new game event with details about itself.
     * 
     * @param type
     *          the type of the event
     * @param details
     *          the details of the event
     */
    public GameEventWithDetailsImpl(final GameEventType type, final B details) {
        super(type);
        this.details = details;
    }

    /**
     * @return the details of the event
     */
    public B getDetails() {
        return this.details;
    }

}
