package puttingchallenge.model.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;

/**
 * Class that implements an element of the game.
 */
public interface GameObject {

    /**
     * Types of the game objects.
     */
    enum GameObjectType { 

        /**
         * The type of the game ball.
         */
        BALL,

        /**
         * The type of the game player.
         */
        PLAYER,

        /**
         * The type of the land in the game.
         */
        LAND,

        /**
         * The type of a wall static obstacle.
         */
        WALL,

        /**
         * The type of a tree static obstacle.
         */
        TREE,

        /**
         * The type of a football ball static obstacle.
         */
        FOOTBALL,

        /**
         * The type of the game hole.
         */
        HOLE;
    }

    /**
     * Sets the coordinates corresponding to the position of the object.
     * @param position
     *          position to assign
     */
    void setPosition(Point2D position);

    /**
     * Sets the velocity of the object.
     * @param vel
     *          velocity vector to assign
     */
    void setVelocity(Vector2D vel);

    /**
     * @return the type of the object.
     */
    GameObjectType getType();

    /**
     * @return the coordinates of the object.
     */
    Point2D getPosition();

    /**
     * @return the velocity of the object.
     */
    Vector2D getVelocity();

    /**
     * Update physic state of the object.
     * 
     * @param dt
     *          instant of time.
     * @param env
     *          environment of the game.
     */
    void updatePhysics(long dt, Environment env);

    /**
     * @return the {@link PhysicsComponent} of the object.
     */
    PhysicsComponent getPhysicsComponent();

    /**
     * @return the {@link GraphicComponent} of the object.
     */
    GraphicComponent getGraphicComponent();

    /**
     * Draw the game object skin in the actual scene.
     *
     * @param graphicsContext
     *          the {@link GraphicsContext} of the {@link Canvas} 
     *          where the skin of the {@link GameObject} will be drawn
     */
    void draw(GraphicsContext graphicsContext);

}
