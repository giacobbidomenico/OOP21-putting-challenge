package puttingchallenge.model.collisions;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * It wraps an ActiveBoundingBox so that its collision can be tested to a PassiveCircleBoundingBox dynamically.
 */
public class ConcreteDynamicBoundingBox implements DynamicBoundingBox {

    private static final long INTERVAL_DELTA = 2;
    private final ActiveBoundingBox box;

    /**
     * Builds a new {@link ConcreteDynamicBoundingBox}.
     * @param box is the active bounding box wrapped
     */
    public ConcreteDynamicBoundingBox(final ActiveBoundingBox box) {
        this.box = box;
    }

    private Optional<Long> testMovingCircle(final PassiveCircleBBTrajectoryBuilder circleBuilder, 
            final long t1, 
            final long t2) {
        final long mid = (t2 + t1) / 2;
        if (!this.box.isColliding(circleBuilder.build(mid))) {
            return Optional.empty();
        }

        if (t2 - t1 < INTERVAL_DELTA) {
            return Optional.ofNullable(t1);
        }
        final Optional<Long> leftResult = testMovingCircle(circleBuilder, t1, mid);
        if (leftResult.isPresent()) {
            return leftResult;
        }
        return testMovingCircle(circleBuilder, mid, t2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionTest collidesWith(final PassiveCircleBBTrajectoryBuilder circleBuilder, final long dt) {
        final Optional<Long> timeOfCollision = this.testMovingCircle(circleBuilder, 0, dt);
        if (timeOfCollision.isEmpty()) {
            return new ConcreteCollisionTest();
        }
        final Point2D lastPosition = circleBuilder.build(timeOfCollision.get()).getPosition();
        Point2D closestPoint = this.box.closestPointOnBBToPoint(lastPosition);
        if (lastPosition == closestPoint) {
            closestPoint = this.box.intersectionToSegment(
                    circleBuilder.build(0).getPosition(), 
                    lastPosition);
        }
        final Vector2D normal = this.box.getNormal(closestPoint);
        return new ConcreteCollisionTest(true, closestPoint, normal, lastPosition);
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

        @Override
        public Optional<Vector2D> getActiveBBSideTanget() {
            if (this.normal != null) {
                return Optional.ofNullable(new Vector2D(-normal.getY(), normal.getX()));
            }
            return Optional.empty();
        }

    }

}
