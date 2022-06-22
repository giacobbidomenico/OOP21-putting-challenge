package puttingchallenge.model.events;

import java.util.List;

/**
 *
 */
public interface ObserverEvents {

    /**
        * Notifies a {@link List} of events. 
        * @param types
        *         types of the events which must be notified.
        */
    void notifyEvents(List<ModelEventType> types);

    /**
     * @return a {@link List} of the events which must be notified.
     */
    List<ModelEventType> getEvents();

}
