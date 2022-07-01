package puttingchallenge.model.events;

import java.util.List;

/**
 *  Interface that defines an event observable of type <A>.
 *  @param <A>
 */
public interface ObservableEvents<A> {

    /**
     * Add an {@link ObserverEvents}.
     * 
     * @param observer
     *            the {@link ObserverEvents} from which events are received
     */
    void addObserver(ObserverEvents<A> observer);

    /**
     * Remove an {@link ObserverEvents}.
     * 
     * @param observer
     *            the {@link ObserverEvents} from which events are received
     */
    void removeObserver(ObserverEvents<A> observer);

    /**
     * @return a {@link List} in which its type is present for each event received
     */
    List<A> eventsReceived();

}
