package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameEngine;
import puttingchallenge.gameobjects.GameObject;
import puttingchallenge.gameobjects.GameObject.GameObjectType;

/**
 * Class that implements the builder of the game environment.
 * 
 */
public class BuilderEnvironmentImpl implements BuilderEnvironment {

    private final GameFactory factory;
    private final List<GameObject> gameObjects;
    private Optional<Rectangle2D> container;
    private Optional<GameObject> ball;
    private Optional<GameObject> player;
    private Optional<GameObject> hole;
    private Optional<GameEngine> controller;

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
        this.controller = Optional.empty();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment container(final Rectangle2D container) {
        if (this.container.isEmpty()) {
            this.container = Optional.of(container);
        }
        return this;
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
    public BuilderEnvironment player(final Point2D pos, final String skinPath, final double w, final double h) {
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
                                                final Rectangle2D dimensions) {
        switch (gameObjectType) {
        case WALL:
            gameObjects.add(factory.createWall(pos, dimensions.getWidth(), dimensions.getHeight()));
            break;
        case TREE:
            gameObjects.add(factory.createTree(pos, dimensions.getWidth(), dimensions.getHeight()));
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
    public BuilderEnvironment controller(final GameEngine controller) {
        if (this.controller.isEmpty()) {
            this.controller = Optional.of(controller);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment hole(final GameObject hole) {
        if (this.hole.isEmpty()) {
            this.hole = Optional.of(hole);
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
                || controller.isEmpty()
                || hole.isEmpty()) {
            throw new IllegalStateException();
        }
        return new EnvironmentImpl(this.container.get(), 
                                   this.ball.get(), 
                                   this.player.get(),
                                   this.hole.get());
    }

}
