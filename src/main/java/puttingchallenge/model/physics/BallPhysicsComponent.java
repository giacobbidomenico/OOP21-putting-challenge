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
    private static final double INCREASE = 1.5;
    private static final double MIN_POTENTIAL_ENERGY = 70;
    private static final double MIN_BOUNCING_DIFFERENCE_FACTOR = 0.8;

    private final double radius;
    private boolean isMoving;
    private Optional<Point2D> lastPos;
    private Optional<ActiveBoundingBox> lastHitbox;

    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param radius
     *          radius of the ball
     */
    public BallPhysicsComponent(final double radius) {
        this.setVelocity(new Vector2D(0, 0));
        this.radius = radius;
        this.lastPos = Optional.empty();
        this.lastHitbox = Optional.empty();
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

            final Optional<CollisionTest> infoOpt = env.checkCollisions(((BallObjectImpl) obj).getHitBox(), clone, obj.getPosition());
            final Point2D nextPos;
            if (infoOpt.isPresent()) {
                final CollisionTest info = infoOpt.get();
 
                final double radius = ((BallObjectImpl) obj).getHitBox().getRadius();
                final Vector2D normale = info.getActiveBBSideNormal().get();
                final Vector2D lastVel = this.getVelocity();

                nextPos = info.getEstimatedPointOfImpact().get();
                nextPos.sumX(normale.getX() * radius * INCREASE);
                nextPos.sumY(normale.getY() * radius * INCREASE);
                nextPos.sumX(-radius);
                nextPos.sumY(-radius);

                final Vector2D finVel = this.velAfterCollision(normale, lastVel);
                this.setVelocity(finVel);
                this.isStopping(nextPos, info.getActiveBoundingBox());
            } else {
                nextPos = this.nextPos(dt, obj.getPosition());
            }

            obj.setPosition(nextPos);
        }
    }

    private Vector2D velAfterCollision(final Vector2D normale, final Vector2D lastVel) {
        double sign = Math.signum(normale.getY()) == -1 ? 1 : -1;
        final double y = lastVel.getY() * (normale.getY() == 0 ? 1 : normale.getY() * sign) * 0.9;
        sign = Math.signum(normale.getX()) == -1 ? 1 : -1;
        final double x = lastVel.getX() * (normale.getX() == 0 ? 1 : normale.getX() * sign) * 0.9;
        return new  Vector2D(x, y);
    }

    private void isStopping(final Point2D pos, final ActiveBoundingBox hitbox) {
        if (this.lastHitbox.isPresent() && this.lastPos.isPresent()) {
            System.out.println(-Y_ACCELERATION * (1 / pos.getY()) * 100);
            if (Point2D.getDistance(pos, this.lastPos.get()) < radius * MIN_BOUNCING_DIFFERENCE_FACTOR
                && hitbox.equals(this.lastHitbox.get())
                && (-Y_ACCELERATION * (1 / pos.getY()) * 100) < MIN_POTENTIAL_ENERGY) {
                this.setVelocity(new Vector2D(0, 0));
            }
        }
        this.lastPos = Optional.of(pos);
        this.lastHitbox = Optional.of(hitbox);
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
        final double t = 0.001 * dt * 1.5;
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
