package puttingchallenge.model;

/**
 * Interface that represent a game event observer.
 */
public interface GameEventListener {

    /**
     * Notifies the occurrence of a game event.
     * 
     * @param e
     *          The {@link GameEvent} occur
     */
    void notifyEvent(GameEvent e);

}
