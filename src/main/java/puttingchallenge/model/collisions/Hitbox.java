package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;

/**
 * Represents a basic hitbox.
 */
public interface Hitbox {

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
