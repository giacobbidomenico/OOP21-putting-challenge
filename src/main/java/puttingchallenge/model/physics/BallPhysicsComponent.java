package puttingchallenge.model.physics;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.Environment;

/**
 * Describes the physical behavior of the ball.
 */
public class BallPhysicsComponent extends AbstractPhysicsComponent {

    private static final double Y_ACCELERATION = 30 * -9.81;
    private static final double FRICTION = 17.1E-6;

    private final double radius;
    private boolean isMoving;

    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param radius
     *          radius of the ball
     */
    public BallPhysicsComponent(final double radius) {
        this.setVelocity(new Vector2D(0, 0));
        this.radius = radius;
    }

    /**
     * @return the radius of the ball.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt, final GameObject obj, final Environment env) {
        if (this.isMoving) {
//            final GameObject clone = new GameFactory().createBall(new Point2D(obj.getPosition()), this.radius);
//            clone.setVelocity(new Vector2D(this.getVelocity()));
//
//            final Optional<Collision> infoOpt = env.checkCollison(clone);
//            final Point2D nextPos;
//            if(infoOpt.isPresent()) {
//                // aggiornare velocità dopo la collisione
//                final Collision info = infoOpt.get();
 
//                obj.setVelocity(info.getVelocity());
//                switch (info.getEdge()) {
//                case 
//                }
//            } else {
//                nextPos = this.nextPos(dt, obj);
//            }
            final Point2D nextPos = this.nextPos(dt, obj);
            this.reduceVel(dt);
            if (obj.getPosition().equals(nextPos)) {
                this.setVelocity(new Vector2D(0, 0));
            } else {
                obj.setPosition(nextPos);
            }
        }
    }

    private Point2D nextPos(final long dt, final GameObject obj) {
        final double t = 0.001 * dt;
        final Point2D curPos = obj.getPosition();
        final Vector2D vel = this.getVelocity();

        final double x = curPos.getX() + (vel.getX() * t);
        final double y = curPos.getY()
                         + (vel.getY() * t)
                         - (0.5 * Y_ACCELERATION * t * t);
        return new Point2D(x, y);
    }

    private void reduceVel(final long dt) {
        double velX = Math.abs(this.getVelocity().getX());
        double velY = this.getVelocity().getY();
        final double t = 0.001 * dt;

        velY -= Y_ACCELERATION * t;
        if (velX != 0) {
            velX -= 3 * Math.PI * FRICTION * velX * this.radius * t;
            if (this.getVelocity().getX() < 0) {
                velX *= -1;
            }
        }
        this.setVelocity(new Vector2D(velX, velY));
    }

    /**
     * Tells if the ball is moving or not.
     * 
     * @return true if the ball is stopped, false otherwise
     */
    public boolean isMoving() {
        return this.isMoving;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Vector2D vel) {
        super.setVelocity(vel);
        if (this.getVelocity().equals(new Vector2D(0, 0))) {
            this.isMoving = false;
        } else {
            this.isMoving = true;
        }
    }
}
