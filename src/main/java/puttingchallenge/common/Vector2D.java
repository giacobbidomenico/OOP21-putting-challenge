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
     * Build a new {@link Vector2D}, copy of the vector given as argument.
     * 
     * @param vector
     *          another instance of {@link Vector2D} class 
     */
    public Vector2D(final Vector2D vector) {
        this.x = vector.x;
        this.y = vector.y;
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

    /**
     * @return module of the vector
     */
    public double getModule() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    /**
     * Compares this vector to the specified object. The result is true if and
     * only if the argument is not null and is an instance of {@link Vector2D} and 
     * contains the same components.
     * 
     * @param obj
     *          the object to compare
     * 
     * @return true if the given object is equal to this vector, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Vector2D) {
            final var v = (Vector2D) obj;
            return this.x == v.x && this.y == v.y;
        }
        return false;
    }
}
