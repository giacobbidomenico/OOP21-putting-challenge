package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;

/**
 * Represents an active circle bounding box.
 */
public class CircleBoundingBox implements ActiveBoundingBox {

    private final Point2D position;
    private final Double radius;

    /**
     * @param position of the center of the circle bounding box
     * @param radius of the center of the circle bounding box
     */
    public CircleBoundingBox(final Point2D position, final double radius) {
        this.position = position;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final PassiveCircleBoundingBox circle) {
        return this.radius + circle.getRadius() >= Point2D.getDistance(this.position, circle.getPosition());
    }

}
