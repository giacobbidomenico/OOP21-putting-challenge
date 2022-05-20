package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject;

/**
 * Represent the physical behavior of the object.
 */
public interface PhysicsComponent {
    /**
     * Update the physical state of the provided game object.
     * 
     * @param dt
     *          instant of time.
     * @param obj
     *          the instance of {@link GameObject} to update
     */
    void update(long dt, GameObject obj);
    /**
     * Set velocity of the object.
     * 
     * @param x
     *          x-component of the 2D vector
     * @param y
     *          y-component of the 2D vector
     */
    void setVelocity(double x, double y);
    /**
     * @return the velocity of the object.
     */
    Vector2D getVelocity();
}
