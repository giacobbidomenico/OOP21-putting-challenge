package puttingchallenge.physics;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject;

/**
 * The physical behavior of the ball.
 */
public class BallPhysicsComponent implements PhysicsComponent {

    private static final double Y_ACCELERATION = 9.81;

    private Vector2D vel;
    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param vel
     *          initial velocity of the ball
     */
    public BallPhysicsComponent(final Vector2D vel) {
        this.vel = vel;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt, final GameObject obj) {
        final Point2D nextPos = this.nextPos(dt, obj);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Vector2D vel) {
        this.vel = vel;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        return this.vel;
    }

    private Point2D nextPos(final long dt, final GameObject obj) {
        final long t = (long) 0.001 * dt;
        final Point2D curPos = obj.getPosition();
        final double x = curPos.getX() + (this.vel.getX() * t);
        final double y = curPos.getY()
                         + (this.vel.getY() * t)
                         - (0.5 * Y_ACCELERATION * t * t);
        return new Point2D(x, y);
    }
}
