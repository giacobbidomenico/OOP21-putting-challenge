package puttingchallenge.model;

import java.util.Optional;

/**
 * Abstract class that represent a {@link GameState}.
 */
public abstract class AbstractGameState implements GameState {
    private final GameStatus status;
    private final GameStateManager stateManager;
    private Optional<Environment> environment;
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
    public Optional<Environment> getEnvironment() {
        return this.environment;
    }

    /**
     * 
     * @param nextStatus
     */
    void leavingState(final GameStatus nextStatus) {
        this.stateManager.switchState(nextStatus);
    }
}
