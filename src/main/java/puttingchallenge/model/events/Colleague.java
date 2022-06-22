package puttingchallenge.model.events;

/**
 * Defines an object that needs to interact with other objects (Colleagues), notifying them the occurred event.
 * A Colleague delegates its interaction with other Colleagues to a Mediator.
 */
public interface Colleague {

    /**
     * @param mediator the mediator to whom to delegate the interaction
     */
    void setMediator(Mediator mediator);

    /**
     * The method to call to notify the Colleague.
     * @param event to be passed
     */
<<<<<<< HEAD
    void notifyEvent(GameEvent event);
=======
    void notifyEvent(GameEvent<?> event);
>>>>>>> eae2128d4fb7f727004426d3f5f2d2380749ea90
}
