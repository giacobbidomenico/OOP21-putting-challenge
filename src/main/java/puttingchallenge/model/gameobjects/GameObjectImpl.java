package puttingchallenge.model.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;


/**
 * Class that implements an element of the game.
 */
public class GameObjectImpl implements GameObject {

    private final GameObjectType type;
    private Point2D pos;
    private boolean isFlipped;
    private final GraphicComponent graph;
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
                          final GraphicComponent graph,
                          final PhysicsComponent phys) {
        this.type = type;
        this.pos = position;
        this.graph = graph;
        this.phys = phys;
        this.isFlipped = false;
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
    @Override
    public PhysicsComponent getPhysicsComponent() {
        return this.phys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFlip(final boolean flip) {
        this.isFlipped = flip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFlipped() {
        return this.isFlipped;
    }

}
