package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject;

/**
 * The physical behavior of the ball.
 */
public class BallPhysicsComponent implements PhysicsComponent {

    private static final double Y_ACCELERATION = 9.81;

    private Vector2D vel;
    private double angle;
    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param vel
     *          initial velocity of the ball
     */
    public BallPhysicsComponent(final Vector2D vel) {
        this.init(vel);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt, final GameObject obj) {
       
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Vector2D vel) {
        this.init(vel);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        return this.vel;
    }

    private void init(final Vector2D vel) {
        this.vel = vel;
        this.angle = Math.acos(this.vel.getX() / this.vel.getModule());
    }
}
