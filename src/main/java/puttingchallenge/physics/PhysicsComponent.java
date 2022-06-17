package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.GameObject;

/**
 * Describes the physical behavior of a {@link GameObject}.
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

    /**
     * @return
     *      true if the ball is moving
     */
    boolean isMoving();

}
