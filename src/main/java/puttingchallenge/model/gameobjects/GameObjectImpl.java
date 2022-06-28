package puttingchallenge.model.gameobjects;

import java.util.Objects;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.DynamicBoundingBox;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;

/**
 * Class that implements an element of the game.
 */
public class GameObjectImpl extends AbstractGameObject {

    private final DynamicBoundingBox hitBox;

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
                          final DynamicBoundingBox hitBox) {
        super(Objects.requireNonNull(type),
              Objects.requireNonNull(position),
              Objects.requireNonNull(graph),
              Objects.requireNonNull(phys));
        this.hitBox = hitBox;
    }

    /**
     * @return the hitbox of the object
     */
    public DynamicBoundingBox getHitBox() {
        return this.hitBox;
    }

}
