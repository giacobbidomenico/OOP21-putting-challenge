package puttingchallenge.physics;

import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject;

/**
 * The physical behavior of the ball.
 */
public class BallPhysicsComponent implements PhysicsComponent {
    private Vector2D vel;
    private Vector2D acc;
    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param vel
     *          initial velocity of the ball
     * @param acc
     *          initial acceleration of the ball
     */
    public BallPhysicsComponent(final Vector2D vel, final Vector2D acc) {
        this.vel = vel;
        this.acc = acc;
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
    public void setVelocity(final double x, final double y) {
        // TODO Auto-generated method stub
        
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        // TODO Auto-generated method stub
        return null;
    }

}
