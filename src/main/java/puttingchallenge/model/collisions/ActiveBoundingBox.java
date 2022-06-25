package puttingchallenge.model.collisions;

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

}
