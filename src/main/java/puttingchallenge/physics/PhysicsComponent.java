package puttingchallenge.physics;

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
}
