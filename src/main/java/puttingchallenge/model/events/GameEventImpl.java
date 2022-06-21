package puttingchallenge.model.events;

/**
 * Implementation of {@link GameEvent} interface.
 * @param <A>, the Enum type subclass that defines the types of events
 */
public class GameEventImpl<A extends Enum<A>> implements GameEvent<A> {

    private final A type;

    /**
     * Build a new game event without details.
     * 
     * @param type
     *          the type of the event
     */
    public GameEventImpl(final A type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A getEventType() {
        return this.type;
    }

}
