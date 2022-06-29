package puttingchallenge.model.events;

import java.util.Optional;

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
     * {@inheritDoc}
     */
    @Override
    public <T> Optional<T> getDetails() {
        try {
            return Optional.of((T) this.details);
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }

}
