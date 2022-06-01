package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Interface that defines the builder of the game environment.
 */
public interface BuilderEnvironment {
    /**
     * Sets the ball configuration.
     * 
     * @param pos
     *          initial position of the ball
     * @param radius
     *          radius of the ball
     * @param vel
     *          initial velocity of the ball
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment ball(Point2D pos, double radius, Vector2D vel);
    /**
     * Sets the player configuration.
     * 
     * @param pos
     *          initial position of the player
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment player(Point2D pos);
    /**
     * Sets the configuration of a new static obstacle.
     * 
     * @param pos
     *          position of the obstacle
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment staticObstacle(Point2D pos);
    /**
     * Builds the game environment.
     * 
     * @return an instance of {@link Environment}, representing the
     *         game environment
     */
    Environment build();
}
