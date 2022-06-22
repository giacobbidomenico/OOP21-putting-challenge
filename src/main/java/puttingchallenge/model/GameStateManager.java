package puttingchallenge.model;

import puttingchallenge.model.events.Colleague;

/**
 * Interface that defines the {@link GameState} manager.
 */
public interface GameStateManager extends Colleague {
    /**
     * Sets a new current{@link GameState}.
     * @param newState
     *          to switch to
     */
    void switchState(GameState newState);
    /**
     * Gets the current {@link GameState}.
     * @return
     *          the current {@link GameState}
     */
    GameState getCurrentState();
}
