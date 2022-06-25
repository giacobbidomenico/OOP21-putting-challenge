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

    /**
     * Represents a collision test between an active bounding box and a passive bounding box.
     */
    interface CollisionTest {

    }
}
