package puttingchallenge.model.gameobjects;

import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.Environment;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;


/**
 * Class that implements an element of the game.
 * @param <H>
 *      the type of the hitbox component of the object
 */
public class GameObjectImpl<H> implements GameObject<H> {

    private final GameObjectType type;
    private Point2D pos;
    private final GraphicComponent graph;
    private final PhysicsComponent phys;
    private final H hitBox;

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
     * @param hitBox
     *                  the hitbox of the object
     */
    public GameObjectImpl(final GameObjectType type,
                          final Point2D position,
                          final GraphicComponent graph,
                          final PhysicsComponent phys,
                          final H hitBox) {
        this.type = Objects.requireNonNull(type);
        this.pos = Objects.requireNonNull(position);
        this.graph = Objects.requireNonNull(graph);
        this.phys = Objects.requireNonNull(phys);
        this.hitBox = Objects.requireNonNull(hitBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        this.pos = position;
        this.hitBox.setPosition(position);
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
    public H getHitBox() {
        return this.hitBox;
    }

}
