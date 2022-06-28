package puttingchallenge.model.events;

import java.util.List;

/**
 * Interface that defines an event observer of type {@link ModelEventType}.
 * @param <A>
 */
public interface ObserverEvents<A> {

    /**
     * Notifies a {@link List} of events. 
     * @param types
     *         types of the events which must be notified.
     */
    void sendModelEvents(List<A> types);

    /**
     * @return a {@link List} of the events which must be notified.
     */
    List<A> getEvents();

}
