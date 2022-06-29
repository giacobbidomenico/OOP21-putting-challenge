package puttingchallenge.model.collisions;

import java.util.Optional;
import java.util.Set;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean bounceAlongTanget() {
        return false;
    }

    private Optional<Point2D> lineLineIntersection(final Point2D pointA1, 
            final Point2D pointA2, 
            final Point2D pointB1, 
            final Point2D pointB2) {
        final Vector2D b = new Vector2D(pointA2.getX() - pointA1.getX(), pointA2.getY() - pointA1.getY());
        final Vector2D d = new Vector2D(pointB2.getX() - pointB1.getX(), pointB2.getY() - pointB1.getY());
        final double cross = b.getX() * d.getY() - b.getY() * d.getX();

        if (cross == 0) {
            return Optional.empty();
        }
        final Vector2D c = new Vector2D(pointB1.getX() - pointA1.getX(), pointB1.getY() - pointA1.getY());
        final double t = (c.getX() * d.getY() - c.getY() * d.getX()) / cross;
        if (t < 0 || t > 1) {
            return Optional.empty();
        }
        final double u = (c.getX() * b.getY() - c.getY() * b.getX()) / cross;
        if (u < 0 || u > 1) {
            return Optional.empty();
        }

        return Optional.ofNullable(new Point2D(pointA1.getX() + t * b.getX(),
                pointA1.getY() + t * b.getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D intersectionToSegment(final Point2D pointA, final Point2D pointB) {
        final Point2D closestPoint = this.closestPointOnBBToPoint(pointA);
        if (pointA.getX() == pointB.getX() || pointA.getY() == pointB.getY()) {
            return closestPoint;
        }
        final Point2D upLeft = new Point2D(this.minimumVertex.getX(), this.maximumVertex.getY());
        final Point2D bottomRight = new Point2D(this.maximumVertex.getX(), this.minimumVertex.getY());

        Optional<Point2D> result = this.lineLineIntersection(pointA, pointB, maximumVertex, bottomRight);
        if (result.isPresent()) {
            return result.get();
        }
        result = this.lineLineIntersection(pointA, pointB, minimumVertex, bottomRight);
        if (result.isPresent()) {
            return result.get();
        }
        result = this.lineLineIntersection(pointA, pointB, upLeft, minimumVertex);
        if (result.isPresent()) {
            return result.get();
        }
        result = this.lineLineIntersection(pointA, pointB, upLeft, maximumVertex);
        return result.get();
    }

}
