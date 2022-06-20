package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.graphics.GraphicComponent;
import puttingchallenge.physics.PhysicsComponent;

/**
 * A generic {@link GameObject}.
 */
public class GenericGameObject extends AbstractGameObject {

    /**
     * Build a new {@link GenericGameObject}.
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
    public GenericGameObject(final GameObjectType type, 
                             final Point2D position, 
                             final GraphicComponent graph, 
                             final PhysicsComponent phys) {
        super(type, position, graph, phys);
    }

}
