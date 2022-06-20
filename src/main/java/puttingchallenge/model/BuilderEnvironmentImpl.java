package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject.GameObjectType;

/**
 * Class that implements the builder of the game environment.
 * 
 */
public class BuilderEnvironmentImpl implements BuilderEnvironment {

    private final GameFactory factory = new GameFactory();
    private final List<GameObject> gameObjects;
    private Optional<Double> percWidth;
    private Optional<Double> percHeight;
    private Optional<GameObject> ball;
    private Optional<GameObject> player;
    private Optional<GameEngine> controller;

    /**
     * Build a new {@link BuilderEnvironmentImpl}.
     * 
     */
    public BuilderEnvironmentImpl() {
        this.percWidth = Optional.empty();
        this.percHeight = Optional.empty();
        this.ball = Optional.empty();
        this.player = Optional.empty();
        this.controller = Optional.empty();
        this.gameObjects = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuilderEnvironment dimension(final double percWidth, 
                                        final double percHeight) {
        if (this.percWidth.isEmpty() && this.percHeight.isEmpty()) {
            this.percHeight = Optional.of(percWidth);
            this.percWidth = Optional.of(percHeight);
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
    public BuilderEnvironment player(final Point2D pos, final String skinPath) {
        if (player.isEmpty()) {
            player = Optional.of(factory.createPlayer(pos, skinPath));
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
    public Environment build() {
        if (this.percWidth.isEmpty() || this.percHeight.isEmpty() || ball.isEmpty() || player.isEmpty() || controller.isEmpty()) {
            throw new IllegalStateException();
        }
        return new EnvironmentImpl(this.percWidth.get(), this.percHeight.get(), this.ball.get(), this.player.get());
    }
}
