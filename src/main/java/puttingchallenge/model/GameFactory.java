package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.GameObject.GameObjectType;

/**
 * factory for all the game objects.
 */
public final class GameFactory {

    private GameFactory() { }

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
    public static GameObject createBall(final Point2D pos,
                                        final double radius,
                                        final Vector2D vel) {
        return new GameObjectImpl(GameObjectType.BALL, pos, vel);
    }

}
