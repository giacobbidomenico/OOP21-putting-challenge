package puttingchallenge.model;

import java.util.Optional;

import puttingchallenge.model.events.ModelEventType;

/**
 * 
 * Interface for maintaining and handling the states of the game.
 *
 */
public interface GameState {
    /**
     * Sets the initial state.
     */
    void initState();
    /**
     * @return
     *          the current {@link GameStatus} of the game
     */
    GameStatus getStatus();
    /**
     * @return
     *          the {@link GameStateManager} object
     */
    GameStateManager getGameStateManager();
    /**
     * @return
     *          the {@link Environment} object
     */
    Optional<Environment> getEnvironment();
    /**
     * Sets the state's environment.
     * @param environment
     *          to set
     */
    void setEnvironment(Optional<Environment> environment);
    /**
     * Notify the intercepted event.
     * @param eventType
     *          of the event intercepted
     */
    void notifyEvents(ModelEventType eventType);
    /**
     * Reads the events sent by the {@link GameState}.
     */
    void receiveEvents();
}
