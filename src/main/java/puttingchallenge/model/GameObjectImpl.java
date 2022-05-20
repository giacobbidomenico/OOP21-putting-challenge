package puttingchallenge.model;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Class that implements an element of the game.
 */
public class GameObjectImpl implements GameObject {
    private final GameObjectType type;
    private Point2D pos;
    private Vector2D vel;
    /**
     * Build a new {@link GameObjectImpl}.
     * 
     * @param type 
     *                 element type
     * @param position
     *                 coordinates of the element
     * @param velocity
     *                 velocity of the object
     */
    public GameObjectImpl(
            final GameObjectType type,
            final Point2D position,
            final Vector2D velocity) {
        this.type = type;
        this.pos = position;
        this.vel = velocity;
    }
    /**
     * {@inheritDoc}
     */
    public void setPosition(final double x, final double y) {
        this.pos = new Point2D(x, y);
    }
    /**
     * {@inheritDoc}
     */
    public void setVector(final double x, final double y) {
        this.vel = new Vector2D(x, y);
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
        return pos;
    }
    /**
     * {@inheritDoc}
     */
    public Vector2D getVelocity() {
        return vel;
    }
}
