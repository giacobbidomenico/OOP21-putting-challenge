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
    public void switchState(final GameStatus status) {
        switch (status) {
            case PLAY:
                this.currentGameState = new GamePlayGameState(this, status);
            break;
            case GAME_OVER:
            case MAIN_MENU:
                this.currentGameState = new ScreenGameState(this, status);
            break;
        default:
            break;
        }
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
    public void notifyEvent(final GameEvent event) {
        this.generalMediator.notifyColleagues(event, this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Environment getCurrentEnvironment() {
        return this.getCurrentState().getEnvironment();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt) {
        this.currentGameState.getEnvironment().update(dt);
    }
}
