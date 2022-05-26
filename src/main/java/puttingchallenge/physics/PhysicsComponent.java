package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
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
     * @param env
     *          environment of the game
     */
    void update(long dt, GameObject obj, Environment env);

    /**
     * Set velocity of the object.
     * 
     * @param vel
     *          velocity vector to assign
     */
    void setVelocity(Vector2D vel);

    /**
     * @return the velocity of the object.
     */
    Vector2D getVelocity();
}
