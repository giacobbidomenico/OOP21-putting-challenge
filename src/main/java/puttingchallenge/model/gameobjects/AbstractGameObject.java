package puttingchallenge.model.gameobjects;

import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;

/**
 * Abstract class that define a generic {@link GameObject}.
 */
public abstract class AbstractGameObject implements GameObject {

    private final GameObjectType type;
    private Point2D pos;
    private final GraphicComponent graph;
    private final PhysicsComponent phys;
    private boolean flip;

    /**
     * Set up a new {@link AbstractGameObject}.
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
    public AbstractGameObject(final GameObjectType type,
                              final Point2D position,
                              final GraphicComponent graph,
                              final PhysicsComponent phys) {
        this.type = Objects.requireNonNull(type);
        this.pos = Objects.requireNonNull(position);
        this.graph = Objects.requireNonNull(graph);
        this.phys = Objects.requireNonNull(phys);
        this.flip = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        this.pos = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Vector2D vel) {
        this.phys.setVelocity(vel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        return this.phys.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final long dt, final Environment env) {
        this.phys.update(dt, this, env);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final GraphicsContext graphicsContext) {
        this.graph.draw(this, graphicsContext);
    }

    /**
     * {@inheritDoc}
     */
    public GraphicComponent getGraphicComponent() {
        return this.graph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent getPhysicsComponent() {
        return this.phys;
    }


    /**
     * {@inheritDoc}
     */
    public GraphicComponent getGraphicComponent() {
        return this.graph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFlip(final boolean flip) {
        this.flip = flip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFlip() {
        return this.flip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(flip, graph, phys, pos, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof GameObject) {
            final GameObject gameObject = (GameObject) obj;
            return flip == gameObject.isFlip() 
                   && graph.equals(gameObject.getGraphicComponent()) 
                   && phys.equals(gameObject.getPhysicsComponent())
                   && pos.equals(gameObject.getPosition()) 
                   && type.equals(gameObject.getType());
        }
        return false;
    }


}
