package puttingchallenge.model;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.graphic.GraphicsComponent;
import puttingchallenge.physics.PhysicsComponent;


/**
 * Class that implements an element of the game.
 */
public class GameObjectImpl implements GameObject {

    private final GameObjectType type;
    private Point2D pos;
    private final GraphicsComponent graph;
    private final PhysicsComponent phys;

    /**
     * Build a new {@link GameObjectImpl}.
     * 
     * @param type 
     *                 element type
     * @param position
     *                 coordinates of the element
     * @param graph
     *                 graphic component of the object
     * @param phys
     *                 physical component of the object
     */
    public GameObjectImpl(final GameObjectType type,
                          final Point2D position,
                          final GraphicsComponent graph,
                          final PhysicsComponent phys) {
        this.type = type;
        this.pos = position;
        this.graph = graph;
        this.phys = phys;
    }

    /**
     * {@inheritDoc}
     */
    public void setPosition(final Point2D position) {
        this.pos = position;
    }

    /**
     * {@inheritDoc}
     */
    public void setVelocity(final Vector2D vel) {
        this.phys.setVelocity(vel);
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
        return this.phys.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    public void updatePhysics(final long dt, final Environment env) {
        phys.update(dt, this, env);
    }

}
