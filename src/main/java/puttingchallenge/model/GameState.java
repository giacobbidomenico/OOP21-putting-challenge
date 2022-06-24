package puttingchallenge.model;

import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.SceneType;

/**
 * 
 * Interface for maintaining and handling the states of the game.
 *
 */
public interface GameState {
    /**
     * Sets the initial state.
     * @return 
     */
    Pair<SceneType, List<GameObject>> initState();
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
