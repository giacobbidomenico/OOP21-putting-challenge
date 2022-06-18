package puttingchallenge.model;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.graphics.BallGraphicComponent;
import puttingchallenge.graphics.TreeGraphicComponent;
import puttingchallenge.graphics.WallGraphicComponent;
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
                                  new BallGraphicComponent(radius),
                                  new BallPhysicsComponent(radius));
    }

    /**
     * Build a new wall in the game.
     * 
     * @param pos
     *          static position of the obstacle
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     * @param w
     *          the width of the wall
     * @param h
     *          the height of the wall
     *
     * @return an instance of {@link GameObject} representing a wall.
     */
    public GameObject createWall(final Point2D pos,
                                 final GraphicsContext gc,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.WALL,
                                  pos, 
                                  new WallGraphicComponent(gc, w, h), 
                                  new StaticPhysicsComponent());
    }

    /**
     * Build a new tree in the game.
     * 
     * @param pos
     *          initial position of the player
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn 
     * @return an instance of {@link GameObject} representing a static obstacle
     */
    public GameObject createStaticObstacle(final Point2D pos, final GraphicsContext gc) {
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

    /**
     * @param pos
     *          initial position of the tree
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     * @param w
     *          the width of the tree
     * @param h
     *          the height of the tree
     *
     * @return an instance of {@link GameObject} representing a tree.
     */
    public GameObject createTree(final Point2D pos,
                                 final GraphicsContext gc,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.TREE,
                                  pos, 
                                  new TreeGraphicComponent(gc, w, h), 
                                  new StaticPhysicsComponent());
    }
}
