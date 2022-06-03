package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Class that implements an element of the game.
 */
public interface GameObject {
    /**
     * types of the game objects.
     */
    enum GameObjectType { BALL, STATIC_OBSTACLE }
    /**
     * Sets the coordinates corresponding to the position of the element.
     * @param position
     *          position to assign
     */
    void setPosition(Point2D position);
    /**
     * Sets the velocity of the element.
     * @param vel
     *          velocity vector to assign
     */
    void setVelocity(Vector2D vel);
    /**
     * @return the type of the element.
     */
    GameObjectType getType();
    /**
     * @return the coordinates of the element.
     */
    Point2D getPosition();
    /**
     * @return the velocity of the element.
     */
    Vector2D getVelocity();
    /**
     * Update physic state of the object.
     * 
     * @param dt
     *          instant of time.
     * @param w
     *          game world.
     */
    void updatePhysics(long dt, Environment w);
}
