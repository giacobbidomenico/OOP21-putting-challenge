package puttingchallenge.model.events;

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
     * @param toRemove the colleague to be removed
     */
    void removeColleague(Colleague toRemove);

    /**
     * @param event represents the event occured that the colleagues wants to send
     * @param sender the colleague who wants to send an event
     */
    void notifyColleagues(GameEvent<?> event, Colleague sender);
}
