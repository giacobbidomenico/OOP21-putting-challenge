package puttingchallenge.model.collisions;

import java.util.Objects;
import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.physics.BallPhysicsComponent;

/**
 * Class that build a {@link PassiveCircleBoundingBox} positioned on a physical trajectory, based on a given time.
 */
public class PassiveCircleBBTrajectoryBuilder {

    private Optional<PassiveCircleBoundingBox> hitBox;
    private Optional<BallPhysicsComponent> physicComponent;
    private Vector2D initialVel;
    private Optional<Point2D> position;

    /**
     * Build a new {@link PassiveCircleBBTrajectoryBuilder}.
     */
    public PassiveCircleBBTrajectoryBuilder() {
        this.hitBox = Optional.empty();
        this.physicComponent = Optional.empty();
        this.position = Optional.empty();
    }

    /**
     * Set the original hitbox.
     * 
     * @param hitbox
     *          the hitbox to be trasleted
     * @throws IllegalAccessException 
     *          if the method is called and the hitbox is already set
     */
    public void setHitbox(final PassiveCircleBoundingBox hitbox) throws IllegalAccessException {
        if (this.hitBox.isEmpty()) {
            this.hitBox = Optional.of(Objects.requireNonNull(hitbox));
        } else {
            throw new IllegalAccessException("Hitbox already set");
        }
    }

    /**
     * Set the physic component.
     * 
     * @param phys
     *          the physic component to calculate the trajectory
     * @throws IllegalAccessException 
     *          if the method is called and the physic component is already set
     */
    public void setPhysic(final BallPhysicsComponent phys) throws IllegalAccessException {
        if (this.physicComponent.isEmpty()) {
            this.physicComponent = Optional.of(Objects.requireNonNull(phys));
            this.initialVel = phys.getVelocity();
        } else {
            throw new IllegalAccessException("Physic component already set");
        }
    }

    /**
     * Set the original position.
     * 
     * @param position
     *          the initial position of the hitbox
     * @throws IllegalAccessException 
     *          if the method is called and the position is already set
     */
    public void setPosition(final Point2D position) throws IllegalAccessException {
        if (this.position.isEmpty()) {
            this.position = Optional.of(Objects.requireNonNull(position));
        } else {
            throw new IllegalAccessException("Position already set");
        }
    }

    /**
     * Build a {@link PassiveCircleBoundingBox} positioned on a physical trajectory in a certain time.
     * 
     * @param dt
     *          delta time
     * @return the builded {@link PassiveCircleBoundingBox}
     */
    public PassiveCircleBoundingBox build(final long dt) {
        if (this.hitBox.isEmpty()
            || this.physicComponent.isEmpty() 
            || this.position.isEmpty()) {
            throw new IllegalStateException();
        } else {
            this.physicComponent.get().setVelocity(initialVel);
            final Point2D pos = this.physicComponent.get().nextPos(dt, this.position.get());
            final double radius = Point2D.getDistance(pos, this.position.get());
            return new ConcretePassiveCircleBoundingBox(pos, radius);
        }
    }

}
