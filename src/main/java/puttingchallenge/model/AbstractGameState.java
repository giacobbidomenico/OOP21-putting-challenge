package puttingchallenge.model;

import java.util.Objects;
import java.util.Optional;

import puttingchallenge.core.GameLoopImpl;

/**
 * Abstract class that represent a {@link GameState}.
 */
public abstract class AbstractGameState implements GameState {
    private final GameStatus status;
    private final GameStateManager stateManager;
    private final Optional<Environment> environment;

    /**
     * 
     * @param manager
     * @param status
     * @param environment
     */
    public AbstractGameState(final GameStateManager manager,
                             final GameStatus status,
                             final Optional<Environment> environment) {
        this.stateManager = manager;
        this.status = status;
        this.environment = environment;
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
