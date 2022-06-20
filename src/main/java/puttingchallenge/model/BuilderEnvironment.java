package puttingchallenge.model;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject.GameObjectType;

/**
 * Interface that defines the builder of the game environment.
 * 
 */
public interface BuilderEnvironment {

    /**
     * Sets the {@link Rectangle2D} that contains the game {@link Environment}.
     * 
     * @param container
     *         the {@link Rectangle2D} that contains the game {@link Environment}
     * @return an instance of {@link BuilderEnvironment},the builder of 
     *         the game {@link Environment}
     */
    BuilderEnvironment container(Rectangle2D container);

    /**
     * Sets the game controller.
     * 
     * @param controller
     *         an instance of {@link GameEngine}, the controller of the application
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game {@link Environment}
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
     *         the game {@link Environment}
     */
    BuilderEnvironment ball(Point2D pos, double radius);

    /**
     * Sets the player configuration.
     * 
     * @param pos
     *          initial position of the player
     * @param skinPath
     *          path of the player's skin
     * @param w
     *          width of the rectangle where the player will be contained
     * @param h
     *          height of the rectangle where the player will be contained
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game {@link Environment}
     */
    BuilderEnvironment player(Point2D pos, String skinPath, double w, double h);

    /**
     * Sets the configuration of a new static obstacle.
     * 
     * @param typeOfObstacle
     *          type of the static obstacle
     * @param pos
     *          position of the obstacle
     * @param dimensions
     *          rectangle where the obstacle will be contained

     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game {@link Environment}
     */
    BuilderEnvironment addStaticObstacle(GameObjectType typeOfObstacle, Point2D pos, Rectangle2D dimensions);

    /**
     * Builds the game {@link Environment}.
     * 
     * @return an instance of {@link Environment}, representing the
     *         game environment
     */
    Environment build();

}
