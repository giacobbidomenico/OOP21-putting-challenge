package puttingchallenge.model.physics;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

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
    private static final int CONSECUTIVE_COLLISIONS = 6;

    private final double radius;
    private boolean isMoving;
    private Queue<Double> lastVelsY;
    private Point2D lastP;

    /**
     * Build a new {@link BallPhysicsComponent}.
     * 
     * @param radius
     *          radius of the ball
     */
    public BallPhysicsComponent(final double radius) {
        this.setVelocity(new Vector2D(0, 0));
        this.radius = radius;
        this.lastVelsY = new LinkedList<>();
        this.lastP = new Point2D(0, 0);
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
            final double vel;
            if (infoOpt.isPresent()) {
                final CollisionTest info = infoOpt.get();

//                final ActiveBoundingBox collision = info.getActiveBoundingBox();
//                if (this.lastCollisions.size() == CONSECUTIVE_COLLISIONS) {
//                    if (this.lastCollisions.stream().allMatch((c) -> c.equals(collision))) {
//                        this.setVelocity(new Vector2D(0, 0));
//                    }
//                } else {
//                    this.lastCollisions.add(collision);
//                }

                final double radius = ((BallObjectImpl) obj).getHitBox().getRadius();
                final Vector2D normale = info.getActiveBBSideNormal().get();
                final Vector2D lastVel = this.getVelocity();

                nextPos = info.getEstimatedPointOfImpact().get();
                nextPos.sumX(normale.getX() * radius * INCREASE);
                nextPos.sumY(normale.getY() * radius * INCREASE);
                nextPos.sumX(-radius);
                nextPos.sumY(-radius);

//                final double y = 2 * normale.getY() * lastVel.getModule() + lastVel.getY();
//                final double x = 2 * normale.getX() * lastVel.getModule() + lastVel.getX();
//                final double rate = lastVel.getModule() / Math.sqrt(x * x + y * y);
//                final Vector2D finalVel = new Vector2D(x * rate, y * rate);
                
//                Vector2D finalVel = new Vector2D(-lastVel.getX(), -lastVel.getY());
//                final double cos = normale.getX() * finalVel.getX() + normale.getY() * finalVel.getY() / normale.getModule() * finalVel.getModule();
//                final double angle = Math.acos(cos);
//                finalVel = new Vector2D(lastVel.getX() * Math.cos(angle), lastVel.getY() * Math.cos(angle));
                
//                double rate = 1 / lastVel.getModule();
//                final double y = 2 * normale.getY() + lastVel.getY() * rate;
//                final double x = 2 * normale.getX() + lastVel.getX() * rate;
//                rate = lastVel.getModule() / Math.sqrt(x * x + y * y);
//                final Vector2D finalVel = new Vector2D(x * rate, y * rate);
                
                System.out.println("x: " + normale.getX() + " y: " + normale.getY() + " lastX: " + lastVel.getX() + " lastY: " + lastVel.getY()); 
                double y = lastVel.getY() * (normale.getY() == 0 ? 1 : normale.getY()) * 0.9;
                double x = lastVel.getX() * (normale.getX() == 0 ? 1 : normale.getX()) * 0.9;

                Vector2D finVel = new  Vector2D(x, y);
                this.setVelocity(finVel);
                System.out.println(" finalX: " + nextPos.getX() + " finalY: " + nextPos.getY());       
                vel = y;
//                if (Point2D.getDistance(nextPos, this.lastP) < radius 
//                    && Point2D.getDistance(nextPos, this.lastP) > radius * 0.001) {
//                    this.setVelocity(new Vector2D(0, 0));
//                }
                
                if ((-Y_ACCELERATION * (1 / nextPos.getY()) * 10) < 10
                    && (Math.abs(finVel.getX()) * Math.abs(finVel.getY())) < 1000) {
                    this.setVelocity(new Vector2D(0, 0));
                }
                this.lastP = nextPos;
            } else {
                nextPos = this.nextPos(dt, obj.getPosition());
                vel = this.getVelocity().getY();
            }

            obj.setPosition(nextPos);
            
            
            
//            if (this.lastVelsY.size() == CONSECUTIVE_COLLISIONS) {
//                if (this.lastVelsY.stream().allMatch((y) -> Math.abs(y - vel) < 20)) {
//                    this.setVelocity(new Vector2D(0, 0));
//                } else {
//                    this.lastVelsY.poll();
//                    this.lastVelsY.add(vel);
//                }
//            } else {
//                this.lastVelsY.add(vel);
//            }
            
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
