package puttingchallenge.core;

import puttingchallenge.model.events.Colleague;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;

/**
 * Controller of the entire application.
 */
public class GameLoopImpl implements GameEngine, Colleague {

    private Mediator mediator;

    /**
     * Builds a {@link GameLoopImpl}.
     */
    public GameLoopImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameLoop() {
        while (true) {

        }
    }

    @Override
    public void setMediator(Mediator mediator) {
        // TODO Auto-generated method stub
    }

    @Override
    public void notifyEvent(GameEvent event) {
        // TODO Auto-generated method stub
    }

}
