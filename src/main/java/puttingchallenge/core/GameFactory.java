package puttingchallenge.core;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.ConcretePassiveCircleBoundingBox;
import puttingchallenge.model.collisions.PassiveCircleBoundingBox;
import puttingchallenge.model.gameobjects.BallObjectImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.GameObjectImpl;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;
import puttingchallenge.model.physics.BallPhysicsComponent;
import puttingchallenge.model.physics.StaticPhysicsComponent;
import puttingchallenge.view.graphics.BallGraphicComponent;
import puttingchallenge.view.graphics.HoleGraphicComponent;
import puttingchallenge.view.graphics.PlayerGraphicComponent;
import puttingchallenge.view.graphics.TreeGraphicComponent;
import puttingchallenge.view.graphics.WallGraphicComponent;

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
        final Point2D center = new Point2D(pos);
        center.sumX(-radius);
        center.sumY(-radius);
        return new BallObjectImpl(GameObjectType.BALL, 
                                  pos, 
                                  new BallGraphicComponent(radius), 
                                  new BallPhysicsComponent(radius),
                                  new ConcretePassiveCircleBoundingBox(center, radius));
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
