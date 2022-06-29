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
     * @return whether this bounding box and circle collide
     */
    boolean isColliding(PassiveCircleBoundingBox circle);

    /**
     * @return true if an object colliding would bounce along the tangent,
     *          false otherwise
     */
    boolean bounceAlongTanget();

    /**
     * @param pointOnActiveBoundingBox
     * @return the normal vector to the ActiveBoundingBox at the point passed
     */
    Vector2D getNormal(Point2D pointOnActiveBoundingBox);

    /**
     * @param point 
     * @return the closest point on the bounding box to points
     */
    Point2D closestPointOnBBToPoint(Point2D point);

    /**
     * @param pointA point of the segment being outside the bounding box
     * @param pointB point of the segment being inside the bounding box
     * @return the point of intersection with the segment
     */
    Point2D intersectionToSegment(Point2D pointA, Point2D pointB);

}
