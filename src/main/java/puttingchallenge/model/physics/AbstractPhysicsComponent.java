package puttingchallenge.model.physics;

import java.util.Objects;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.gameobjects.GameObject;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(vel);
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
            return phys.getVelocity().equals(this.vel);
        }
        return false;
    }

}
