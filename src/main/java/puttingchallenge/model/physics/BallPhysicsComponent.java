package puttingchallenge.model.physics;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.gameobjects.BallObjectImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.Environment;
import puttingchallenge.model.collisions.ActiveBoundingBox;
import puttingchallenge.model.collisions.DynamicBoundingBox.CollisionTest;

/**
 * Describes the physical behavior of the ball.
 */
public class BallPhysicsComponent extends AbstractPhysicsComponent {

    private static final double Y_ACCELERATION = 30 * -9.81;
    private static final double FRICTION = 17.1E-6;

    private final double radius;
    private boolean isMoving;
    private Optional<ActiveBoundingBox> lastCollision;

    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param radius
     *          radius of the ball
     */
    public BallPhysicsComponent(final double radius) {
        this.setVelocity(new Vector2D(0, 0));
        this.radius = radius;
        this.lastCollision = Optional.empty();
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
            final BallPhysicsComponent clone = new BallPhysicsComponent(radius);
            clone.setVelocity(new Vector2D(this.getVelocity()));

            final Optional<CollisionTest> infoOpt = env.checkCollisions(((BallObjectImpl) obj).getHitBox(), clone, obj.getPosition(), dt);
            final Point2D nextPos;
            Optional<ActiveBoundingBox> collision = Optional.empty();
            if (infoOpt.isPresent()) {
                final CollisionTest info = infoOpt.get();

                collision = Optional.of(info.getActiveBoundingBox());

                final double radius = ((BallObjectImpl) obj).getHitBox().getRadius();
                nextPos = info.getPassiveBoxPositionBeforeCollisions().get();
                nextPos.sumX(-radius);
                nextPos.sumY(radius);

                final Vector2D normale = info.getActiveBBSideNormal().get();
                final Vector2D lastVel = this.getVelocity();
                final double rate = lastVel.getModule() / normale.getModule();
                final double y = normale.getY() * rate;
                final double x = normale.getX() * rate;
                this.setVelocity(new Vector2D(x, y));
                obj.setPosition(nextPos);
            } else {
                this.lastCollision = Optional.empty();
                nextPos = this.nextPos(dt, obj.getPosition());
            }

            if (this.lastCollision.isPresent()
                && collision.isPresent()
                && this.lastCollision.get().equals(collision.get())) {
                this.setVelocity(new Vector2D(0, 0));
            } else {
                obj.setPosition(nextPos);
            }
        }
    }



    /**
     * Given a delta time, it calculates the next position of the object, starting from an initial position.
     * Follow the formulas of the motion of the projectile in a viscous medium.
     * 
     * @param dt
     *          delta time
     * @param curPos
     *          starting position
     * @return the next expected position
     */
    public Point2D nextPos(final long dt, final Point2D curPos) {
        final double t = 0.001 * dt;
        final Vector2D vel = this.getVelocity();

        this.reduceVel(dt);
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
