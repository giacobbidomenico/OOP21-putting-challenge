package puttingchallenge.model.events;

import puttingchallenge.model.GameEvent;

/**
 * Defines an object (Mediator) that encapsulates the interactions between a set of colleagues.
 * A colleague delegates its interaction with other colleagues to a Mediator.
 */
public interface Mediator {

    /**
     * @param newColleague is a colleague
     */
    void addColleague(Colleague newColleague);

    /**
     * @param event 
     * represents the event occured that the colleagues wants to send
     */
    void notifyColleagues(GameEvent<?, ?> event);
}
