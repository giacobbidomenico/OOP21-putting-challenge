package puttingchallenge.model;

import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import puttingchallenge.model.events.Colleague;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.SceneType;

/**
 * Interface that represent the various states in the game.
 */
public interface GameState extends Colleague {
    /**
     * Initialize the state.
     * @return 
     *          a {@link Pair} that contains the {@link SceneType} and a {@link List} of {@link GameObject}
     */
    Pair<SceneType, List<GameObject>> initState();
    /**
     * @return
     *          the associated {@link GameStatus}
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
     * Sets the {@link Environment} of the state.
     * @param environment
     *          to set
     */
    void setEnvironment(Optional<Environment> environment);
    /**
     * Notify the intercepted model event.
     * @param eventType
     *          of the event intercepted
     */
    void notifyEvents(ModelEventType eventType);
    /**
     * Reads the events received form the {@link Environment}.
     */
    void receiveEvents();
    /**
     * The last operations to be done by the current state before ending.
     * @param nextStatus
     *          the next status of the game
     */
    void leavingState(GameStatus nextStatus);
}
