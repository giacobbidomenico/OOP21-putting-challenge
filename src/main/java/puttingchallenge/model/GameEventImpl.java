package puttingchallenge.model;

/**
 * Implementation of {@link GameEvent} interface.
 */
public class GameEventImpl<A extends Enum<A>> implements GameEvent<A> {

    private final GameEventType type;

    /**
     * Build a new game event without details.
     * 
     * @param type
     *          the {@link GameEventType} of the event
     */
    public GameEventImpl(final GameEventType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A getEventType() {
        // TODO Auto-generated method stub
        return null;
    }

}
