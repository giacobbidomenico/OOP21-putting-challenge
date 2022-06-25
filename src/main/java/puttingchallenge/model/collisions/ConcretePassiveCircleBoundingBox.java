package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;

/**
 * Represents a concrete {@link PassiveCircleBoundingBox}.
 */
public class ConcretePassiveCircleBoundingBox implements PassiveCircleBoundingBox {

    private Point2D position;
    private final double radius;

    /**
     * @param point the position of the center of the bounding box
     * @param radius
     */
    public ConcretePassiveCircleBoundingBox(final Point2D point, final double radius) {
        this.position = point;
        this.radius = radius;
    }

    /**
     * @param position of the center of the bounding box
     * @return
     */
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRadius() {
        return this.radius;
    }

}
