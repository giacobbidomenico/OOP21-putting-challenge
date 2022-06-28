package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameFactory;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;
import puttingchallenge.model.gameobjects.PlayerObject;

/**
 * Class that implements the builder of the game environment.
 * 
 */
public class BuilderEnvironmentImpl implements BuilderEnvironment {

    private final GameFactory factory;
    private final List<GameObject> gameObjects;
    private Optional<Rectangle2D> container;
    private Optional<GameObject> ball;
    private Optional<PlayerObject> player;
    private Optional<GameObject> hole;

    /**
     * Build a new {@link BuilderEnvironmentImpl}.
     * 
     */
    public BuilderEnvironmentImpl() {
        this.factory = new GameFactory();
        this.gameObjects = new LinkedList<>();
        this.container = Optional.empty();
        this.ball = Optional.empty();
        this.player = Optional.empty();
        this.hole = Optional.empty();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment addContainer(final Rectangle2D container) {
        if (this.container.isEmpty()) {
            this.container = Optional.of(container);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment addBall(final Point2D pos,
                                   final double radius) {
        if (ball.isEmpty()) {
            ball = Optional.of(factory.createBall(pos, radius));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment addPlayer(final Point2D pos, final String skinPath, final double w, final double h) {
        if (player.isEmpty()) {
            player = Optional.of(factory.createPlayer(pos, skinPath, w, h));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment addStaticObstacle(final GameObjectType gameObjectType, 
                                                final Point2D pos,
                                                final double w,
                                                final double h) {
        switch (gameObjectType) {
        case LAND:
            gameObjects.add(factory.createLand(pos, w, h));
            break;
        case WALL:
            gameObjects.add(factory.createWall(pos, w, h));
            break;
        case TREE:
            gameObjects.add(factory.createTree(pos, w, h));
            break;
        case ICEBERG:
            gameObjects.add(factory.createIceberg(pos, w, h));
            break;
        default:
            throw new IllegalArgumentException();
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment addHole(final Point2D pos, 
                                      final double w, 
                                      final double h) {
        if (this.hole.isEmpty()) {
            this.hole = Optional.of(this.factory.createHole(pos, w, h));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment build() {
        if (this.container.isEmpty()
                || ball.isEmpty() 
                || player.isEmpty() 
                || hole.isEmpty()) {
            throw new IllegalStateException();
        }
        return new EnvironmentImpl(this.container.get(), 
                                   this.ball.get(), 
                                   this.player.get(),
                                   this.gameObjects,
                                   this.hole.get());
    }

}
