package puttingchallenge.model;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.graphics.BallGraphicComponent;
import puttingchallenge.graphics.GraphicComponent;
import puttingchallenge.model.GameObject.GameObjectType;
import puttingchallenge.physics.BallPhysicsComponent;
import puttingchallenge.physics.StaticPhysicsComponent;

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
<<<<<<< HEAD
     *          static position of the obstacle
=======
     *          initial position of the obstacle
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     * 
>>>>>>> master
     * @return an instance of {@link GameObject} representing a static obstacle
     */
    public GameObject createStaticObstacle(final Point2D pos, final GraphicsContext gc) {
        return new GameObjectImpl(GameObjectType.STATIC_OBSTACLE, 
                                  pos,
<<<<<<< HEAD
                                  new GraphicsComponent(),
                                  new StaticPhysicsComponent());
    }
    /**
     * Build the player.
     * 
     * @param pos
     *          initial position of the player
     * @return an instance of {@link GameObject} representing the player
     */
    public GameObject createPlayer(final Point2D pos) {
        return new GameObjectImpl(GameObjectType.PLAYER,
                pos,
                new GraphicsComponent(),
                new StaticPhysicsComponent());
=======
                                  new GraphicComponent(),
                                  new StaticObstaclePhysicsComponent());
>>>>>>> master
    }
}
