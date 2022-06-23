package puttingchallenge.model;

import puttingchallenge.model.events.GameEventType;

/**
 * Abstract class that represent a {@link GameState}.
 */
public abstract class AbstractGameState implements GameState {
    private GameStatus status;
    private GameStateManager stateManager;
    private Environment environment;
    /**
     * 
     * @param manager
     * @param status
     */
    public AbstractGameState(final GameStateManager manager, final GameStatus status) {
        this.stateManager = manager;
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStatus getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStateManager getGameStateManager() {
        return this.stateManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment getEnvironment() {
        return this.environment;
    }

    /**
     * 
     * @param nextStatus
     */
    void leavingState(final GameStatus nextStatus) {
        this.stateManager.switchState(nextStatus);
    }
    /**
     * Notify the intercepted event.
     * @param eventType
     *          of the event intercepted
     */
    abstract void notifyEvents(GameEventType eventType);
    /**
     * Reads the events sent by the {@link GameState}.
     */
    abstract void receiveEvents();

}
