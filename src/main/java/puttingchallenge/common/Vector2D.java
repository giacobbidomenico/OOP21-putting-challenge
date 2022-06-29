package puttingchallenge.common;

import java.io.Serializable;

/**
 * 
 * 2-dimensional vector.
 *
 */
public class Vector2D implements Serializable {

    private static final long serialVersionUID = -6448133475513092884L;
    private double x, y;

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
     * Sets the x component of the vector.
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
    }
    /**
     * Sets the y component of the vector.
     * @param y
     */
    public void setY(final double y) {
        this.y = y;
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
     * Adds the supplied value to the x-component of the vector.
     * 
     * @param value
     *          the value to sum
     */
    public void sumX(final double value) {
        this.x += value;
    }

    /**
     * Adds the supplied value to the y-component of the vector.
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
            return Double.compare(this.x, v.getX()) == 0 
                    && Double.compare(this.y, v.getY()) == 0;
        }
        return false;
    }
    /**
     * Return a new {@link Vector2D} object starting from pointA to pointB.
     * @param pointA
     * @param pointB
     * @return
     *          the new {@link Vector2D}
     */
    public static Vector2D getVectorFrom(final Point2D pointA, final Point2D pointB) {
        return new Vector2D(pointA.getX() - pointB.getX(), pointA.getY() - pointB.getY());
    }
    /**
     * Flips the vector components.
     */
    public void flipVector() {
        this.setX(-x);
        this.setY(-y); 
    }

    /**
     * @param vector 
     * @return the dot product with vector
     */
    public double dotProduct(final Vector2D vector) {
        return this.getX() * vector.getX() + this.getY() * vector.getY();
    }
}
