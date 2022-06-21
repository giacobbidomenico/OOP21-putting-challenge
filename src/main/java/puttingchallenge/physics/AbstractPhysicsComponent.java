package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.gameobjects.GameObject;
import puttingchallenge.model.Environment;

/**
 * Abstract class that describes the physics of moving {@link GameObject}.
 */
public abstract class AbstractPhysicsComponent implements PhysicsComponent {

    private Vector2D vel;

    /**
     * {@inheritDoc}
     */
    public abstract void update(long dt, GameObject obj, Environment env);

    /**
     * {@inheritDoc}
     */
    public void setVelocity(final Vector2D vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D getVelocity() {
        return this.vel;
    }

}
