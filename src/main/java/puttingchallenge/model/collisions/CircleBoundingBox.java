package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getNormal(final Point2D pointOnActiveBoundingBox) {
        final double x = -this.position.getX() + pointOnActiveBoundingBox.getX();
        final double y = -this.position.getY() + pointOnActiveBoundingBox.getY();
        return new Vector2D(x / Math.sqrt(y * y + x * x), y / Math.sqrt(y * y + x * x));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D closestPointOnBBToPoint(final Point2D point) {
        final Point2D diff = new Point2D(
                point.getX() - this.position.getX(), 
                point.getY() - this.position.getY());

        final double module = Math.sqrt(
                diff.getX() * diff.getX() 
                + diff.getY() * diff.getY());

        return new Point2D(
                this.position.getX() + diff.getX() / module * this.radius,
                this.position.getY() + diff.getY() / module * this.radius);
    }

}
