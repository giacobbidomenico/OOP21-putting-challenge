package puttingchallenge.model.collisions;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * It wraps an ActiveBoundingBox so that its collision can be tested to a PassiveCircleBoundingBox dynamically.
 */
public class ConcreteDynamicBoundingBox implements DynamicBoundingBox {

    private static final long INTERVAL_DELTA = 1;
    private final ActiveBoundingBox box;

    /**
     * Builds a new {@link ConcreteDynamicBoundingBox}.
     * @param box is the active bounding box wrapped
     */
    public ConcreteDynamicBoundingBox(final ActiveBoundingBox box) {
        this.box = box;
    }

    private Optional<Point2D> findFirstPointOfCollision(
            final PassiveCircleBBTrajectoryBuilder circleBuilder, 
            final long t0, 
            final long t1) {

        PassiveCircleBoundingBox lastPosition = circleBuilder.build((t1 - t0) / 2);
        if (!this.box.isColliding(lastPosition)) {
            return Optional.empty();
        }
//        long temp1 = t1;
//        long temp0 = t0;
//
//        while (temp1 - temp0 > INTERVAL_DELTA) {
//            final PassiveCircleBoundingBox leftHitbox = circleBuilder.build(((temp1 - temp0) / 2 - temp0) / 2);
//            final PassiveCircleBoundingBox rightHitbox = circleBuilder.build((temp1 - (temp1 - temp0) / 2) / 2);
//
//            if (!this.box.isColliding(rightHitbox)) {
//                lastPosition = rightHitbox;
//            } else {
//                temp0 = (temp1 - temp0) / 2;
//            }
//            if (!this.box.isColliding(leftHitbox)) {
//                lastPosition = leftHitbox;
//            } else {
//                temp1 = (temp1 - temp0) / 2;
//            }
//        }
        lastPosition = circleBuilder.build(t0);
        return Optional.ofNullable(lastPosition.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionTest collidesWith(final PassiveCircleBBTrajectoryBuilder circleBuilder, final long deltaT) {
        final Optional<Point2D> lastPosition = this.findFirstPointOfCollision(circleBuilder, 0, deltaT);

        if (lastPosition.isEmpty()) {
            return new ConcreteCollisionTest();
        }

        final Point2D closestPoint = this.box.closestPointOnBBToPoint(lastPosition.get());
        final Vector2D normal = this.box.getNormal(closestPoint);
        return new ConcreteCollisionTest(true, closestPoint, normal, lastPosition.get());
    }

    /**
     * Represents a concrete collision test between a {@link PassiveCircleBoundingBox} and a {@link ActiveBoundingBox}.
     */
    public class ConcreteCollisionTest implements CollisionTest {

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
            this.estimatedPointOfImpact = estimatedPointOfImpact;
            this.normal = normal;
            this.positionBeforeCollision = positionBeforeCollision;
        }

        /**
         * Builds {@link ConcreteCollisionTest} in case no collision has occurred.
         */
        private ConcreteCollisionTest() {
            this(false, null, null, null);
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
