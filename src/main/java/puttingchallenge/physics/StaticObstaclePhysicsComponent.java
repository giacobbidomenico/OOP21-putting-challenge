package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.GameObject;

/**
 * Describes the physical behavior of a fixed {@link GameObject}.
 */
public class StaticObstaclePhysicsComponent implements PhysicsComponent {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Vector2D vel) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        return new Vector2D(0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt, final GameObject obj, final Environment env) { }

}
