package puttingchallenge.model;
/**
 * Implementation of {@link GameStateManager} interface.
 */
public class GameStateManagerImpl implements GameStateManager {
    private GameState currentGameState;
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

}
