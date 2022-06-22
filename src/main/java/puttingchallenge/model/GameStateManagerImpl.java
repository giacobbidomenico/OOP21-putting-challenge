package puttingchallenge.model;

import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;

/**
 * Implementation of {@link GameStateManager} interface.
 */
public class GameStateManagerImpl implements GameStateManager {
    private GameState currentGameState;
    private Mediator generalMediator;
    /**
     * {@inheritDoc}
     */
    @Override
    public void switchState(final GameState newState) {
        this.currentGameState = newState;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getCurrentState() {
        return this.currentGameState;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
        this.generalMediator = mediator;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent<?> event) {
        this.generalMediator.notifyColleagues(event, this);
    }

}
