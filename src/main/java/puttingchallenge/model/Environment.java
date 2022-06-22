package puttingchallenge.model;

import java.util.List;

import javafx.geometry.Rectangle2D;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Interface that defines the game environment.
 */
public interface Environment {
    /**
     * Update the game environment.
     * 
     */
    void update();

    /**
     * Check if there has been a collision between several {@link GameObject}.
     * 
     * @return true if a collision occurred,
     *         false otherwise.
     */
    boolean checkCollisions();

    /**
     * Adds a static obstacle to the game environment.
     * 
     * @param obstacle
     *               static obstacle to add
     */
    void addStaticObstacle(GameObject obstacle);

    /**
     * @return the {@link GameObject} corresponding to the ball in the 
     *         game environment
     */
    GameObject getBall();

    /**
     * @return the {@link GameObject} corresponding to the player in the 
     *         game environment
     */
    GameObject getPlayer();

    /**
     * @return the {@link GameObject} corresponding to the hole in the 
     *         game environment
     */
    GameObject getHole();

    /**
     * @return a list of {@link GameObject} corresponding to the static 
     *         obstacles in the game environment
     */
    List<GameObject> getStaticObstacles();

    /**
     * Stop the movement of the ball.
     */
    void notifyBallStopped();

    /**
     * Move the player next to the ball.
     */
    void movePlayer();

    /**
     * @return the {@link Rectangle2D} inside which there is the game {@link Environment}.
     */
    Rectangle2D getContainer();

    /**
     * Notifies if an event has been intercepted.
     */
    void notifyEvents();

    /**
     * Reads the events sent by the {@link GameState}.
     */
    void receiveEvents();

    /**
     * Configure the {@link ObservableEvents}, that allows the
     * communication between {@link Environment} and {@link GameState}.
     * 
     * @param observable
     *           the {@link ObservableEvents}, that allows the communication.
     */
    void configureObservable(ObservableEvents observable);
}
