package puttingchallenge.model.collisions;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * It wraps an ActiveBoundingBox so that its collision can be tested to a PassiveCircleBoundingBox dynamically.
 */
public class ConcreteDynamicBoundingBox implements DynamicBoundingBox {

    private static final long INTERVAL_DELTA = 21;
    private final ActiveBoundingBox box;

    /**
     * Builds a new {@link ConcreteDynamicBoundingBox}.
     * @param box is the active bounding box wrapped
     */
    public ConcreteDynamicBoundingBox(final ActiveBoundingBox box) {
        this.box = box;
    }

    private Optional<Point2D> findFirstPointOfCollision(
            final PassiveCircleBBTrajectoryBuilder circleBuilder) {

        PassiveCircleBoundingBox lastPosition = circleBuilder.build(INTERVAL_DELTA);
        if (!this.box.isColliding(lastPosition)) {
            return Optional.empty();
        }
        lastPosition = circleBuilder.build(0);
        return Optional.ofNullable(lastPosition.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionTest collidesWith(final PassiveCircleBBTrajectoryBuilder circleBuilder) {
        final Optional<PassiveCircleBoundingBox> lastPos = Optional.of(circleBuilder.build(INTERVAL_DELTA));
        final Optional<Point2D> lastPosition;
        if (this.box.isColliding(lastPos.get())) {
            lastPosition = Optional.ofNullable(circleBuilder.build(0).getPosition());
        } else {
            lastPosition = Optional.empty();
        }


//        final Optional<Point2D> lastPosition = this.findFirstPointOfCollision(circleBuilder);

        if (lastPosition.isEmpty()) {
            return new ConcreteCollisionTest();
        }

        final Point2D closestPoint = this.box.closestPointOnBBToPoint(lastPosition.get());
        final Vector2D normal;
        normal = this.box.getNormal(closestPoint);
        return new ConcreteCollisionTest(true, closestPoint, normal, lastPosition.get());
    }

    /**
     * Represents a concrete collision test between a {@link PassiveCircleBoundingBox} and a {@link ActiveBoundingBox}.
     */
    public final class ConcreteCollisionTest implements CollisionTest {

        private final boolean hasCollided;
        private final Point2D estimatedPointOfImpact;
        private final Vector2D normal;
        private final Point2D positionBeforeCollision;

        /**
         * Builds {@link ConcreteCollisionTest} in case a collision has occurred.
         * @param hasCollisionOccurred
         * @param estimatedPointOfImpact
         * @param normal
         * @param positionBeforeCollision
         */
        private ConcreteCollisionTest(final boolean hasCollisionOccurred, 
                final Point2D estimatedPointOfImpact, 
                final Vector2D normal, 
                final Point2D positionBeforeCollision) {
            this.hasCollided = hasCollisionOccurred;
            this.estimatedPointOfImpact = new Point2D(estimatedPointOfImpact);
            this.normal = new Vector2D(normal);
            this.positionBeforeCollision = new Point2D(positionBeforeCollision);
        }

        /**
         * Builds {@link ConcreteCollisionTest} in case no collision has occurred.
         */
        private ConcreteCollisionTest() {
            this.hasCollided = false;
            this.estimatedPointOfImpact = null;
            this.normal = null;
            this.positionBeforeCollision = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isCollisionOccurred() {
            return this.hasCollided;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ActiveBoundingBox getActiveBoundingBox() {
            return box;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Optional<Point2D> getEstimatedPointOfImpact() {
            return Optional.ofNullable(this.estimatedPointOfImpact);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Optional<Vector2D> getActiveBBSideNormal() {
            return Optional.ofNullable(this.normal);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Optional<Point2D> getPassiveBoxPositionBeforeCollisions() {
            return Optional.ofNullable(this.positionBeforeCollision);
        }

    }

}
