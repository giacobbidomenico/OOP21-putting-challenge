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

}
