package puttingchallenge.common;

import java.io.Serializable;

/**
 * 
 * 2-dimensional vector.
 *
 */
public class Vector2D implements Serializable {

    private static final long serialVersionUID = -6448133475513092884L;
    private final double x, y;
    /**
     * Build a new {@link Vector2D}.
     * 
     * @param x
     *          x-component of the 2D vector
     * @param y
     *          y-component of the 2D vector
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Vector2D (" + x + ", " + y + ")";
    }
    /**
     * @return x-component of the 2D vector
     */
    public double getX() {
        return x;
    }
    /**
     * @return y-component of the 2D vector
     */
    public double getY() {
        return y;
    }
}
