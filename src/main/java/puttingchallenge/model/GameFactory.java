package puttingchallenge.model;

import puttingchallenge.common.Point2D;
<<<<<<< HEAD
=======
import puttingchallenge.gameobjects.BallGameObject;
import puttingchallenge.gameobjects.GameObject;
import puttingchallenge.gameobjects.GenericGameObject;
import puttingchallenge.gameobjects.PlayerObject;
>>>>>>> aim
import puttingchallenge.gameobjects.GameObject.GameObjectType;
import puttingchallenge.gameobjects.GameObject;
import puttingchallenge.gameobjects.GameObjectImpl;
import puttingchallenge.graphics.BallGraphicComponent;
import puttingchallenge.graphics.PlayerGraphicComponent;
import puttingchallenge.graphics.TreeGraphicComponent;
import puttingchallenge.graphics.WallGraphicComponent;
import puttingchallenge.graphics.HoleGraphicComponent;
import puttingchallenge.physics.BallPhysicsComponent;
import puttingchallenge.physics.StaticPhysicsComponent;

/**
 * Factory class for all the game objects.
 * 
 */
public class GameFactory {

    /**
     * Build the ball of the game.
     * 
     * @param pos
     *          initial position of the ball
     * @param radius
     *          radius of the ball
     * 
     * @return an instance of {@link GameObject} representing the ball
     */
    public GameObject createBall(final Point2D pos, final double radius) {
        return new GameObjectImpl(GameObjectType.BALL, 
                                  pos, 
                                  new BallGraphicComponent(radius), 
                                  new BallPhysicsComponent(radius));
    }

    /**
     * Build the player.
     * 
     * @param pos
     *          initial position of the player
     * @param skinPath
     *          path of the player's skin
     * @param w
     *          the width of the tree
     * @param h
     *          the height of the tree
     * @return an instance of {@link GameObject} representing the player
     */
    public GameObject createPlayer(final Point2D pos, final String skinPath, final double w, final double h) {
        return new GameObjectImpl(GameObjectType.PLAYER,
                                  pos,
                                  new PlayerGraphicComponent(skinPath, w, h),
                                  new StaticPhysicsComponent());
    }

    /**
     * Build a new wall in the game.
     * 
     * @param pos
     *          static position of the obstacle
     * @param w
     *          the width of the wall
     * @param h
     *          the height of the wall
     *
     * @return an instance of {@link GameObject} representing a wall.
     */
    public GameObject createWall(final Point2D pos,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.WALL,
                                  pos, 
                                  new WallGraphicComponent(w, h), 
                                  new StaticPhysicsComponent());
    }

    /**
     * Build a new tree in the game.
     * 
     * @param pos
     *          initial position of the tree
     * @param w
     *          the width of the tree
     * @param h
     *          the height of the tree
     *
     * @return an instance of {@link GameObject} representing a tree.
     */
    public GameObject createTree(final Point2D pos,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.TREE,
                                  pos, 
                                  new TreeGraphicComponent(w, h), 
                                  new StaticPhysicsComponent());
    }

    /**
     * Build a new hole in the game.
     * 
     * @param pos
     * @param w
     * @param h
     * @return an instance of {@link GameObject} representing a hole.
     */
    public GameObject createHole(final Point2D pos,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.HOLE, 
                                  pos, 
                                  new HoleGraphicComponent(w, h), 
                                  new StaticPhysicsComponent());
    }

}
