package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.GameObject.GameObjectType;

/**
 * Class that implements the builder of the game environment.
 * 
 */
public class BuilderEnvironmentImpl implements BuilderEnvironment {

    private final GameFactory factory = new GameFactory();
    private final List<GameObject> gameObjects;
    private Optional<GameObject> ball;
    private Optional<GameObject> player;

    /**
     * Build a new {@link BuilderEnvironmentImpl}.
     * 
     */
    public BuilderEnvironmentImpl() {
        this.ball = Optional.empty();
        this.player = Optional.empty();
        this.gameObjects = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment ball(final Point2D pos,
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
    public BuilderEnvironment player(final Point2D pos) {
        if (player.isEmpty()) {
            player = Optional.of(factory.createPlayer(pos));
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
        case WALL:
            gameObjects.add(factory.createWall(pos, w, h));
            break;
        case TREE:
            gameObjects.add(factory.createTree(pos, w, h));
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
    public Environment build() {
        if (ball.isEmpty() || player.isEmpty()) {
            throw new IllegalStateException();
        }
        return new EnvironmentImpl(this.ball.get(), this.player.get());
    }

}
