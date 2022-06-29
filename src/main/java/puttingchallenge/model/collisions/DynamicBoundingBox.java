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
     * @param deltaT time from last frame
     * @return details about the collision
     */
    CollisionTest collidesWith(PassiveCircleBBTrajectoryBuilder circle, long dt);

    /**
     * Represents a collision test between an active bounding box and a passive bounding box.
     */
    interface CollisionTest {
        /**
         * @return whether the collision has occurred
         */
        boolean isCollisionOccurred();

        /**
         * @return the active bounding box related to the test
         */
        ActiveBoundingBox getActiveBoundingBox();

        /**
         * @return the estimated point of impact, empty if the collision has not occurred
         */
        Optional<Point2D> getEstimatedPointOfImpact();

        /**
         * @return the normal of the colliding side of the passive bounding box
         */
        Optional<Vector2D> getActiveBBSideNormal();

        /**
         * @return the tangent to the point of collision
         */
        Optional<Vector2D> getActiveBBSideTanget();

        /**
         * @return the estimated position of the passive bounding box at the first time of collision
         * empty if the collision has not occurred
         */
        Optional<Point2D> getPassiveBoxPositionBeforeCollisions();
    }
}
