package puttingchallenge.model;

import java.util.List;
import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

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
     * @param pos
     *          position of the static obstacle
     */
    void addStaticObstacle(Point2D pos);
    /**
     * Sets the configuration of the ball.
     * 
     * @param pos
     *          initial position of the ball
     * @param radius
     *          radius of the ball
     * @param vel
     *          initial velocity of the ball
     */
    void setBall(Point2D pos, double radius, Vector2D vel);
    /**
     * Sets the configuration of the player.
     * 
     * @param pos
     *          initial position of the player
     */
    void setPlayer(Point2D pos);
    /**
     * @return the {@link GameObject} corresponding to the ball in the 
     *         game environment
     */
    Optional<GameObject> getBall();
    /**
     * @return the {@link GameObject} corresponding to the player in the 
     *         game environment
     */
    Optional<GameObject> getPlayer();
    /**
     * @return a list of {@link GameObject} corresponding to the obstacles in the
     *         game environment
     */
    List<GameObject> getObstacles();
    /**
     * Stop the movement of the ball.
     */
    void notifyBallStopped();
}
