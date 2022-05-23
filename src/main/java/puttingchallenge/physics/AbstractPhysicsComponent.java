package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject;

/**
 * Abstract class that describes the physics of all GameObjects.
 */
public abstract class AbstractPhysicsComponent implements PhysicsComponent {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void update(long dt, GameObject obj);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void setVelocity(double x, double y);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Vector2D getVelocity();
}
