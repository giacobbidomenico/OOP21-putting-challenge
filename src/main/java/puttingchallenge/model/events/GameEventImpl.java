package puttingchallenge.model.events;

/**
 * Implementation of {@link GameEvent} interface.
 */
public class GameEventImpl implements GameEvent {

    private final GameEventType type;

    /**
     * Build a new game event without details.
     * 
     * @param type
     *          the type of the event
     */
    public GameEventImpl(final GameEventType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEventType getEventType() {
        return this.type;
    }

}
