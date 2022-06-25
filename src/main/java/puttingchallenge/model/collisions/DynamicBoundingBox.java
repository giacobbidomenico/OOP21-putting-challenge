package puttingchallenge.model.collisions;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Represents a decorator for an ActiveBoundingBox.
 */
public interface DynamicBoundingBox {

    /**
     * @param circle the bounding box on which to check the collision
     * @return details about the collision
     */
    CollisionTest collidesWith(PassiveCircleBoundingBox circle);

    protected default CollisionTest getCollisionOccurredInfo(final ActiveBoundingBox active, 
                final PassiveCircleBoundingBox passive, 
                final Point2D pointOfImpact, 
                final Vector2D normal, 
                final Point2D position) {
        return new CollisionTest(active, passive, pointOfImpact, normal, position);
    }

    protected default CollisionTest getCollisionNotOccurredInfo(final ActiveBoundingBox active, 
            final PassiveCircleBoundingBox passive, 
            final Point2D pointOfImpact, 
            final Vector2D normal, 
            final Point2D position) {
        return new CollisionTest(active, passive);
    }

    /**
     * Represents a collision test between an active bounding box and a passive bounding box.
     */
    class CollisionTest {

    }
}
