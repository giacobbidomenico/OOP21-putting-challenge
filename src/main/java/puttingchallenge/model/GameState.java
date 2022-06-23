package puttingchallenge.model;

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
    Environment getEnvironment();
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
