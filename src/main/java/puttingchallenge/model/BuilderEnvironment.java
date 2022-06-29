package puttingchallenge.model;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;

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
    BuilderEnvironment addContainer(Rectangle2D container);

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
    BuilderEnvironment addBall(Point2D pos, double radius);

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
     * @param flip
     *          flip of the player
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game {@link Environment}
     */
    BuilderEnvironment addPlayer(Point2D pos, 
                                 String skinPath, 
                                 double w, 
                                 double h, 
                                 boolean flip);

    /**
     * Sets the configuration of a new static obstacle.
     * 
     * @param typeOfObstacle
     *          type of the static obstacle
     * @param pos
     *          position of the obstacle
     * @param w
     *          width of the obstacle
     * @param h
     *          height of the obstacle

     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game {@link Environment}
     */
    BuilderEnvironment addStaticObstacle(GameObjectType typeOfObstacle, Point2D pos, double w, double h);

    /**
     * Sets the hole configuration.
     * 
     * @param pos
     *          initial position of the hole
     * @param w
     *          width of the rectangle where the hole will be contained
     * @param h
     *          height of the rectangle where the hole will be contained
     * 
     * @return an instance of {@link BuilderEnvironment} ,the builder of 
     *         the game {@link Environment}
     */
    BuilderEnvironment addHole(Point2D pos, double w, double h);

    /**
     * Builds the game {@link Environment}.
     * 
     * @return an instance of {@link Environment}, representing the
     *         game {@link Environment}
     */
    Environment build();

}
