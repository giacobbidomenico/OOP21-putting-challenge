package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Interface that defines the game environment.
 */
public interface Environment {
    /**
     * Update the game environment.
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
}
