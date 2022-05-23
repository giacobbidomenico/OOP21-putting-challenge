package puttingchallenge.physics;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject;

/**
 * The physical behavior of the ball.
 */
public class BallPhysicsComponent extends AbstractPhysicsComponent {
    private static final double Y_ACCELERATION = 9.81;
    private static final double FRICTION = 17.1E-6;

    private Vector2D vel;
    private final double radius;
    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param vel
     *          initial velocity of the ball
     * @param radius
     *          radius of the ball
     */
    public BallPhysicsComponent(final Vector2D vel, final double radius) {
        this.vel = vel;
        this.radius = radius;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt, final GameObject obj, final Word w) {
        final Point2D nextPos = this.nextPos(dt, obj);
        final Point2D prevPos = obj.getPosition();
        final Vector2D newVel;
        final double velX = obj.getVelocity().getX()
                            - (6 * Math.PI * FRICTION * obj.getVelocity().getX() * this.radius);
        velX = velX > 0 ? 0 : velX;
        
        if(collison) {
            
        } else {
            newVel = new Vector2D(obj.getVelocity().getX(),
                                  obj.getVelocity().getY() - Y_ACCELERATION * 0.001 * dt);
        }
        obj.setPosition(nextPos);
        obj.setVelocity(newVel);
        
        if ((velX == 0) && (prevPos.getY() == nextPos.getY())) {
            w.notifyBallStopped();
        }
    }
    /**
     * {@inheritDoc}
     */
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
