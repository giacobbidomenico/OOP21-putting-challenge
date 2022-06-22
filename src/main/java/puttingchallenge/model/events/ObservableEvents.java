package puttingchallenge.model.events;

import java.util.List;

/**
 *  Interface that defines an event observable of type {@link ModelEventType}.
 */
public interface ObservableEvents {

    /**
     * Add an {@link ObserverEvents}.
     * 
     * @param observer
     *            the {@link ObserverEvents} from which events are received
     */
    void addObserver(ObserverEvents observer);

    /**
     * Remove an {@link ObserverEvents}.
     * 
     * @param observer
     *            the {@link ObserverEvents} from which events are received
     */
    void removeObserver(ObserverEvents observer);

    /**
     * @return a {@link List} in which its type is present for each event received
     */
    List<ModelEventType> eventsRecieved();

}
