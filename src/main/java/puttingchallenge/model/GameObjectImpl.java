package puttingchallenge.model;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Class that implements an element of the game.
 */
public class GameObjectImpl implements GameObject {
    private final GameObjectType type;
    private Point2D position;
    private Vector2D vector;
    /**
     * Build a new {@link GameObjectImpl}.
     * 
     * @param type 
     *                 element type
     * @param position
     *                 coordinates of the element
     * @param vector
     *                 components of the vector
     */
    public GameObjectImpl(
            final GameObjectType type,
            final Point2D position,
            final Vector2D vector) {
        this.type = type;
        this.position = position;
        this.vector = vector;
    }
    /**
     * {@inheritDoc}
     */
    public void setPosition(final double x, final double y) {
        this.position = new Point2D(x, y);
    }
    /**
     * {@inheritDoc}
     */
    public void setVector(final double x, final double y) {
        this.vector = new Vector2D(x, y);
    }
    /**
     * {@inheritDoc}
     */
    public GameObjectType getType() {
        return type;
    }
    /**
     * {@inheritDoc}
     */
    public Point2D getPosition() {
        return position;
    }
    /**
     * {@inheritDoc}
     */
    public Vector2D getVector() {
        return vector;
    }
}
