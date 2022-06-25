package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;

/**
 * Represents a bounding box on which to verify the collision,
 * it only has geometric data about the bounding box, it doesn't do any 
 * active procedure to verify the collision, hence "passive".
 */
public interface PassiveCircleBoundingBox {

    /**
     * @return the radius of the bounding box
     */
    double getRadius();

    /**
     * @param position of the center of the bounding box
     */
    void setPosition(Point2D position);

    /**
     * 
     * @return the position of the center of the bounding box
     */
    Point2D getPosition();
}
