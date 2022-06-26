package puttingchallenge.model.collisions;

import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Represents a rectangle bounding box whose sides are parallel to the axis of the plane.
 */
public class AxisAlignedBoundingBox implements ActiveBoundingBox {

    private final Point2D minimumVertex;
    private final Point2D maximumVertex;

    /**
     * @param minimumVertex bottom-left vertex
     * @param maximumVertex top-right vertex
     */
    public AxisAlignedBoundingBox(final Point2D minimumVertex, final Point2D maximumVertex) {
        this.minimumVertex = minimumVertex;
        this.maximumVertex = maximumVertex;
    }

    /**
     * @param upLeftVertex bottom-left vertex
     * @param height 
     * @param width
     */
    public AxisAlignedBoundingBox(final Point2D upLeftVertex, final double height, final double width) {
        this.minimumVertex = new Point2D(upLeftVertex.getX(), upLeftVertex.getY() + height);
        this.maximumVertex = new Point2D(minimumVertex.getX() + width, upLeftVertex.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D closestPointOnBBToPoint(final Point2D point) {
        Point2D closestPoint = new Point2D(point.getX(), point.getY());
        if (closestPoint.getX() < this.minimumVertex.getX()) {
                closestPoint = new Point2D(this.minimumVertex.getX(), closestPoint.getY());
        }
        if (closestPoint.getX() > this.maximumVertex.getX()) {
                closestPoint = new Point2D(this.maximumVertex.getX(), closestPoint.getY());
        }
        if (closestPoint.getY() > this.minimumVertex.getY()) {
                closestPoint = new Point2D(closestPoint.getX(), this.minimumVertex.getY());
        }
        if (closestPoint.getY() < this.maximumVertex.getY()) {
                closestPoint = new Point2D(closestPoint.getX(), this.maximumVertex.getY());
        }
        return closestPoint;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final PassiveCircleBoundingBox circle) {
        final Point2D closestPointOnAABB = this.closestPointOnBBToPoint(circle.getPosition());
        return circle.getRadius() >= Point2D.getDistance(closestPointOnAABB, circle.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getNormal(final Point2D pointOnActiveBoundingBox) {
        if (pointOnActiveBoundingBox.getX() == minimumVertex.getX()) {
            return new Vector2D(-1, 0);
        }
        if (pointOnActiveBoundingBox.getY() == minimumVertex.getY()) {
            return new Vector2D(0, 1);
        }
        if (pointOnActiveBoundingBox.getX() == maximumVertex.getX()) {
            return new Vector2D(1, 0);
        }
        if (pointOnActiveBoundingBox.getY() == maximumVertex.getY()) {
            return new Vector2D(0, -1);
        }
        return null;
    }

}
