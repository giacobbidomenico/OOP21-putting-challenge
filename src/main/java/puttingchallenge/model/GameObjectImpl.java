package puttingchallenge.model;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import rollball.graphics.GraphicsComponent;
import rollball.input.InputComponent;
import rollball.physics.PhysicsComponent;

/**
 * Class that implements an element of the game.
 */
public class GameObjectImpl implements GameObject {
    private final GameObjectType type;
    private Point2D pos;
    private Vector2D vel;
    private GraphicsComponent graph;
    private PhysicsComponent phys;
    /**
     * Build a new {@link GameObjectImpl}.
     * 
     * @param type 
     *                 element type
     * @param position
     *                 coordinates of the element
     * @param velocity
     *                 velocity of the object
     * @param graph
     *                 graphic component of the object
     * @param phys
     *                 physical component of the object
     */
    public GameObjectImpl(final GameObjectType type,
                          final Point2D position,
                          final Vector2D velocity,
                          final GraphicsComponent graph,
                          final PhysicsComponent phys) {
        this.type = type;
        this.pos = position;
        this.vel = velocity;
        this.graph = graph;
        this.phys = phys;
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
    public void setVelocity(final double x, final double y) {
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
