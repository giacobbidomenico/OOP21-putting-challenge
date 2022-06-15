package puttingchallenge.model;

/**
 * Interface that represent a game event observer.
 */
public interface WorldEventListener {

    /**
     * Notifies the occurrence of a game event.
     * 
     * @param e
     *          The {@link WordEvent} occur
     */
    void notifyEvent(WorldEvent e);

}
