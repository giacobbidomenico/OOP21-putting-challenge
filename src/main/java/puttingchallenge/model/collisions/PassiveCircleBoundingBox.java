package puttingchallenge.model.collisions;

/**
 * Represents a bounding box on which to verify the collision,
 * it only has geometric data about the bounding box, it doesn't do any 
 * active procedure to verify the collision, hence "passive".
 */
public interface PassiveCircleBoundingBox extends Hitbox {

    /**
     * @return the radius of the bounding box
     */
    double getRadius();
}
