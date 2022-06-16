package puttingchallenge.model;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.graphics.BallGraphicComponent;
import puttingchallenge.graphics.GraphicComponent;
import puttingchallenge.model.GameObject.GameObjectType;
import puttingchallenge.physics.BallPhysicsComponent;
import puttingchallenge.physics.StaticObstaclePhysicsComponent;

/**
 * Factory class for all the game objects.
 */
public class GameFactory {

    /**
     * Build the ball of the game.
     * 
     * @param pos
     *          initial position of the ball
     * @param radius
     *          radius of the ball
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     * 
     * @return an instance of {@link GameObject} representing the ball
     */
    public GameObject createBall(final Point2D pos,
                                 final double radius,
                                 final GraphicsContext gc) {
        return new GameObjectImpl(GameObjectType.BALL,
                                  pos,
                                  new BallGraphicComponent(gc, radius),
                                  new BallPhysicsComponent(radius));
    }
    /**
     * Build a new static obstacle of the game.
     * 
     * @param pos
     *          initial position of the obstacle
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     * 
     * @return an instance of {@link GameObject} representing a static obstacle
     */
    public GameObject createStaticObstacle(final Point2D pos, final GraphicsContext gc) {
        return new GameObjectImpl(GameObjectType.STATIC_OBSTACLE, 
                                  pos,
                                  new GraphicComponent(),
                                  new StaticObstaclePhysicsComponent());
    }
}
