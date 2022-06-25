package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Represents a bounding box which verify whether it collides with a passive bounding box
 * hence "active".
 */
public interface ActiveBoundingBox {

    /**
     * 
     * @param circle
     * @return whether this boounding box and circle collide
     */
    boolean isColliding(PassiveCircleBoundingBox circle);

    /**
     * @param pointOnActiveBoundingBox
     * @return the normal vector to the ActiveBoundingBox at the point passed
     */
    Vector2D getNormal(Point2D pointOnActiveBoundingBox);

}
