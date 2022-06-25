package puttingchallenge.common;

import java.io.Serializable;

/**
 * 
 * 2-dimensional point.
 *
 */
public class Point2D implements Serializable {

    private static final long serialVersionUID = -6448133475513092884L;
    private double x, y;

    /**
     * Build a new {@link Point2D}.
     * 
     * @param x
     *          abscissa of the 2D point
     * @param y
     *          ordinate of the 2D point
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Build a new {@link Point2D}, copy of the point given as argument.
     * 
     * @param point
     *          another instance of {@link Point2D} class 
     */
    public Point2D(final Point2D point) {
        this.x = point.x;
        this.y = point.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Point2D (" + x + ", " + y + ")";
    }

    /**
     * @return abscissa of the 2D point
     */
    public double getX() {
        return x;
    }

    /**
     * @return ordinate of 2D point
     */
    public double getY() {
        return y;
    }

    /**
     * Adds the supplied value to the abscissa of the point.
     * 
     * @param value
     *          the value to sum
     */
    public void sumX(final double value) {
        this.x += value;
    }

    /**
     * Adds the supplied value to the ordinate of the point.
     * 
     * @param value
     *          the value to sum
     */
    public void sumY(final double value) {
        this.y += value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    /**
     * Compares this point to the specified object. The result is true if and
     * only if the argument is not null and is an instance of {@link Point2D} and 
     * contains the same coordinates.
     * 
     * @param obj
     *          the object to compare
     * 
     * @return true if the given object is equal to this point, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Point2D) {
            final var p = (Point2D) obj;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    /**
     * Get the distance between two {@link Point2D}.
     * @param pointA
     * @param pointB
     * @return
     *          the distance between point A and B
     */
    public static double getDistance(final Point2D pointA, final Point2D pointB) {
        return new Vector2D(pointA.getX() - pointB.getX(), pointA.getY() - pointB.getY()).getModule();
    }

    /**
     * @param pointA
     * @param pointB
     * @return the dot product between pointA and pointB
     */
    public static double dotProduct(final Point2D pointA, final Point2D pointB) {
        return pointA.getX() * pointB.getX() + pointA.getY() * pointB.getY();
    }
}
