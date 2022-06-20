package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.graphics.GraphicComponent;
import puttingchallenge.physics.PhysicsComponent;

/**
 * The {@link GameObject} of a ball.
 */
public class BallGameObject extends AbstractGameObject {

    private final double radius;

    /**
     * Build a new {@link BallGameObject}.
     * 
     * @param position
     *                 coordinates of the ball
     * @param radius   radius of the ball
     * @param graph
     *                 graphic component of the object
     * @param phys
     *                 physical component of the object
     */
    public BallGameObject(final Point2D position,
                          final double radius,
                          final GraphicComponent graph, 
                          final PhysicsComponent phys) {
        super(GameObjectType.BALL, position, graph, phys);
        this.radius = radius;
    }

    /**
     * @return the radius of the ball.
     */
    public double getRadius() {
        return this.radius;
    }
}
