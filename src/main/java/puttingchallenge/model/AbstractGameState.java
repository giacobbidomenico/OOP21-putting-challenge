package puttingchallenge.model;

/**
 * Abstract class that represent a {@link GameState}.
 */
public abstract class AbstractGameState implements GameState {
    private GameStatus status;
    private GameStateManager stateManager;
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
     * 
     * @param nextStatus
     */
    void leavingState(final GameStatus nextStatus) {
        this.stateManager.switchState(nextStatus);
    }

}
