package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.graphic.GraphicsComponent;
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
     * @param vel
     *          initial velocity of the ball
     * @return an instance of {@link GameObject} representing the ball
     */
    public GameObject createBall(final Point2D pos,
                                        final double radius,
                                        final Vector2D vel) {
        return new GameObjectImpl(GameObjectType.BALL,
                                  pos,
                                  new GraphicsComponent(),
                                  new BallPhysicsComponent(vel, radius));
    }
    /**
     * Build a new static obstacle of the game.
     * 
     * @param pos
     *          static position of the obstacle
     * @return an instance of {@link GameObject} representing a static obstacle
     */
    public GameObject createStaticObstacle(final Point2D pos) {
        return new GameObjectImpl(GameObjectType.STATIC_OBSTACLE, 
                                  pos,
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
    }
}
