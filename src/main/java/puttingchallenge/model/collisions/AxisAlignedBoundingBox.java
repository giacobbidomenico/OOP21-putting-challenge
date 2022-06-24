package puttingchallenge.model.collisions;

import puttingchallenge.common.Point2D;

/**
 * Represents a rectangle bounding box whose sidea are parallel to the axis of the lane.
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
     * @param minimumVertex bottom-left vertex
     * @param height 
     * @param width
     */
    public AxisAlignedBoundingBox(final Point2D minimumVertex, final double height, final double width) {
        this.minimumVertex = minimumVertex;
        this.maximumVertex = new Point2D(minimumVertex.getX() + height, minimumVertex.getY() + width);
    }

    private Point2D closestPointToPoint(final Point2D point) {
        Point2D closestPoint = new Point2D(point.getX(), point.getY());
        if (closestPoint.getX() < this.minimumVertex.getX()) {
                closestPoint = new Point2D(this.minimumVertex.getX(), closestPoint.getY());
        }
        if (closestPoint.getX() > this.maximumVertex.getX()) {
                closestPoint = new Point2D(this.maximumVertex.getX(), closestPoint.getY());
        }
        if (closestPoint.getY() < this.minimumVertex.getY()) {
                closestPoint = new Point2D(closestPoint.getX(), this.minimumVertex.getY());
        }
        if (closestPoint.getY() > this.maximumVertex.getY()) {
                closestPoint = new Point2D(closestPoint.getX(), this.maximumVertex.getY());
        }
        return closestPoint;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final PassiveCircleBoundingBox circle) {
        final Point2D closestPointOnAABB = this.closestPointToPoint(circle.getPosition());
        return circle.getRadius() >= Point2D.getDistance(closestPointOnAABB, circle.getPosition());
    }

}
