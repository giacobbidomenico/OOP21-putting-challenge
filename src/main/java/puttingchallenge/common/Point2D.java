package puttingchallenge.common;

import java.io.Serializable;


/**
 * 
 * 2-dimensional point.
 *
 */
public class Point2D implements Serializable {

    private static final long serialVersionUID = -6448133475513092884L;
    private final double x, y;

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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    /**
     * Compares this point to the specified object. The result is true if and
     * only if the argument is not null and is a instance of {@link Point2D} and 
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

}
