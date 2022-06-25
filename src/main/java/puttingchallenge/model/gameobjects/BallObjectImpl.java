package puttingchallenge.model.gameobjects;

import java.util.Objects;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.PassiveCircleBoundingBox;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;

/**
 * Class that represent the ball game object.
 */
public class BallObjectImpl extends AbstractGameObject {

    private final PassiveCircleBoundingBox hitBox;

    /**
     * Build a new {@link BallObjectImpl}.
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
    public BallObjectImpl(final GameObjectType type,
                          final Point2D position,
                          final GraphicComponent graph,
                          final PhysicsComponent phys,
                          final PassiveCircleBoundingBox hitBox) {
        super(Objects.requireNonNull(type),
        Objects.requireNonNull(position),
        Objects.requireNonNull(graph),
        Objects.requireNonNull(phys));
        this.hitBox = Objects.requireNonNull(hitBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        super.setPosition(position);
        this.hitBox.setPosition(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PassiveCircleBoundingBox getHitBox() {
        return this.hitBox;
    }

}
