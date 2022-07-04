package puttingchallenge.core;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.AxisAlignedBoundingBox;
import puttingchallenge.model.collisions.CircleBoundingBox;
import puttingchallenge.model.collisions.ConcreteDynamicBoundingBox;
import puttingchallenge.model.collisions.ConcretePassiveCircleBoundingBox;
import puttingchallenge.model.gameobjects.BallObjectImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.GameObjectImpl;
import puttingchallenge.model.gameobjects.PlayerObject;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;
import puttingchallenge.model.physics.BallPhysicsComponent;
import puttingchallenge.model.physics.StaticPhysicsComponent;
import puttingchallenge.view.graphics.BallGraphicComponent;
import puttingchallenge.view.graphics.FootballGraphicComponent;
import puttingchallenge.view.graphics.HoleGraphicComponent;
import puttingchallenge.view.graphics.LandGraphicComponent;
import puttingchallenge.view.graphics.PlayerGraphicComponent;
import puttingchallenge.view.graphics.TreeGraphicComponent;
import puttingchallenge.view.graphics.WallGraphicComponent;

/**
 * Factory class for all the game objects.
 * 
 */
public class GameFactory {

    private static final double BALL_GRAPHIC_FACTOR = 1.27;
    private static final double RECT_GRAPHIC_FACTOR = 1.05;

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
                                  new BallGraphicComponent(radius * BALL_GRAPHIC_FACTOR),
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
     *          the width of the player
     * @param h
     *          the height of the player
     * @param flip
     *          flip of the player

     * @return an instance of {@link GameObject} representing the player
     */
    public PlayerObject createPlayer(final Point2D pos, 
                                     final String skinPath, 
                                     final double w, 
                                     final double h, 
                                     final boolean flip) {
        final var player = new PlayerObject(GameObjectType.PLAYER, 
                                            pos, 
                                            new PlayerGraphicComponent(skinPath, w, h), 
                                            new StaticPhysicsComponent(), 
                                            new ConcreteDynamicBoundingBox(new AxisAlignedBoundingBox(pos, h, w)),
                                            w,
                                            h);
        player.setFlip(flip);
        return player;
    }

    /**
     * Build the land in the game.
     * 
     * @param pos
     *          static position of the land
     * @param w
     *          the width of the land
     * @param h
     *          the height of the land
     *
     * @return an instance of {@link GameObject} representing the land.
     */
    public GameObject createLand(final Point2D pos,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.LAND,
                                  pos, 
                                  new LandGraphicComponent(w, h), 
                                  new StaticPhysicsComponent(),
                                  new ConcreteDynamicBoundingBox(new AxisAlignedBoundingBox(pos, h, w)));
    }

    /**
     * Build a new wall in the game.
     * 
     * @param pos
     *          static position of the wall
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
                                  new StaticPhysicsComponent(),
                                  new ConcreteDynamicBoundingBox(new AxisAlignedBoundingBox(pos, h, w)));
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
                                  new StaticPhysicsComponent(),
                                  new ConcreteDynamicBoundingBox(new CircleBoundingBox(new Point2D(pos.getX() + w / 2, pos.getY() + w / 2), w / 2)));
    }

    /**
     * Build a new football ball in the game.
     *
     * @param pos
     *          position of the football ball
     * @param w
     *          width of the football ball
     * @param h
     *          height of the football ball
     *
     * @return an instance of {@link GameObject} representing a football ball.
     */
    public GameObject createFootball(final Point2D pos,
                                     final double w,
                                     final double h) {
        return new GameObjectImpl(GameObjectType.FOOTBALL, 
                                  pos,
                                  new FootballGraphicComponent(w, h),
                                  new StaticPhysicsComponent(),
                                  new ConcreteDynamicBoundingBox(new CircleBoundingBox(new Point2D(pos.getX() + w / 2, pos.getY() + w / 2), w / 2)));
    };

    /**
     * Build a new hole in the game.
     * 
     * @param pos
     *          position of the hole
     * @param w
     *          width of the hole
     * @param h
     *          height of the hole
     *
     * @return an instance of {@link GameObject} representing a hole.
     */
    public GameObject createHole(final Point2D pos,
                                 final double w,
                                 final double h) {
        return new GameObjectImpl(GameObjectType.HOLE, 
                                  pos, 
                                  new HoleGraphicComponent(w, h * RECT_GRAPHIC_FACTOR), 
                                  new StaticPhysicsComponent(),
                                  new ConcreteDynamicBoundingBox(new AxisAlignedBoundingBox(pos, h, w)));
    }

}
