package puttingchallenge.model.physics;

import java.util.Objects;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Describes the physical behavior of a fixed {@link GameObject}.
 */
public class StaticPhysicsComponent implements PhysicsComponent {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(new Vector2D(0, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof PhysicsComponent) {
            final PhysicsComponent phys = (PhysicsComponent) obj;
            if (phys.getVelocity().equals(new Vector2D(0, 0))) {
                return true;
            }
        }
        return false;
    }

}
