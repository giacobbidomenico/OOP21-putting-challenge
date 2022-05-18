package puttingchallenge.common;

import java.io.Serializable;
/**
 * 
 * 
 *
 */
public class Point2D implements Serializable {

    private static final long serialVersionUID = -6448133475513092884L;
    private final double x, y;
    /**
     * 
     * @param x
     * @param y
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * 
     */
    @Override
    public String toString() {
        return "Point2D (" + x + ", " + y + ")";
    }
    /**
     * 
     * @return x
     */
    public double getX() {
        return x;
    }
    /**
     * 
     * @return y
     */
    public double getY() {
        return y;
    }

}
