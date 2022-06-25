package puttingchallenge.model.collisions;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * It wraps an ActiveBoundingBox so that its collision can be tested to a PassiveCircleBoundingBox dynamically.
 */
public class ConcreteDynamicBoundingBox implements DynamicBoundingBox {

    private final ActiveBoundingBox box;

    /**
     * Builds a new {@link ConcreteDynamicBoundingBox}.
     * @param box is the active bounding box wrapped
     */
    public ConcreteDynamicBoundingBox(final ActiveBoundingBox box) {
        this.box = box;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionTest collidesWith(final PassiveCircleBoundingBox circle) {
        return null;
    }

    /**
     * Represents a concrete collision test between a {@link PassiveCircleBoundingBox} and a {@link ActiveBoundingBox}.
     */
    public class ConcreteCollisionTest implements CollisionTest {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isCollisionOccurred() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ActiveBoundingBox getActiveBoundingBox() {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Optional<Point2D> getEstimatedPointOfImpact() {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Optional<Vector2D> getActiveBBSideNormal() {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Optional<Point2D> getPassiveBoxPositionBeforeCollisions() {
            // TODO Auto-generated method stub
            return null;
        }

    }
}
