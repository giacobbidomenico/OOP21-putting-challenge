package puttingchallenge.model.events;

import puttingchallenge.model.events.GameEvent;

/**
 * 
 */
public class ConcreteMediator implements Mediator {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColleague(Colleague newColleague) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeColleague(Colleague toRemove) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyColleagues(GameEvent<?, ?> event, Colleague sender) {

    }

}
