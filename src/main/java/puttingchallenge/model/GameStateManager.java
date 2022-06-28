package puttingchallenge.model;

import java.util.Optional;

import puttingchallenge.model.events.Colleague;
import puttingchallenge.model.events.GameEvent;

/**
 * Interface that defines the {@link GameState} manager.
 */
public interface GameStateManager extends Colleague {
    /**
     * Sets the initial state of the game.
     */
    void initState();
    /**
     * Sets a new current {@link GameState} and send the {@link GameEvent} to sets the appropriate scene.
     * @param status
     *          to switch to
     */
    void switchState(GameStatus status);
    /**
     * Gets the current {@link GameState}.
     * @return
     *          the current {@link GameState} object
     */
    GameState getCurrentState();
    /**
     * Gets the current {@link Environment} in the current {@link GameState}.
     * @return
     *          the current {@link Environment} object
     */
    Optional<Environment> getCurrentEnvironment();
    /**
     * Method that updates the physics state of the {@link GameObject}s.
     * @param dt
     *          time elapsed since the last frame
     */
    void update(long dt);
}
