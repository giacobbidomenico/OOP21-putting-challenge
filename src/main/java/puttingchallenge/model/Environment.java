package puttingchallenge.model;

import java.util.List;

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
     * @return a list of {@link GameObject} corresponding to the static 
     *         obstacles in the game environment
     */
    List<GameObject> getStaticObstacles();
    /**
     * Stop the movement of the ball.
     */
    void notifyBallStopped();
}
