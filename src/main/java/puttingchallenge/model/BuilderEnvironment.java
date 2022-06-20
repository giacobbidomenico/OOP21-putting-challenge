package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject.GameObjectType;

/**
 * Interface that defines the builder of the game environment.
 * 
 */
public interface BuilderEnvironment {

    /**
     * Sets the size of the game environment.
     * 
     * @param percWidth
     *                width of the game environment in percent
     * @param percHeight
     *                height of the game environment in percent
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment dimension(double percWidth, double percHeight);

    /**
     * Sets the game controller.
     * 
     * @param controller
     *         an instance of {@link GameEngine}, the controller of the application
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment controller(GameEngine controller);

    /**
     * Sets the ball configuration.
     * 
     * @param pos
     *          initial position of the ball
     * @param radius
     *          radius of the ball
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment ball(Point2D pos, double radius);

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
     * @param typeOfObstacle
     *          type of the static obstacle
     * @param pos
     *          position of the obstacle
     * @param w
     *          width of the rectangle where the obstacle will be contained
     * @param h
     *          height of the rectangle where the obstacle will be contained
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game environment
     */
    BuilderEnvironment addStaticObstacle(GameObjectType typeOfObstacle, Point2D pos, double w, double h);

    /**
     * Builds the game environment.
     * 
     * @return an instance of {@link Environment}, representing the
     *         game environment
     */
    Environment build();

}
