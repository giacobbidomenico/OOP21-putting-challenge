package puttingchallenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Class that implements the game environment.
 */
public class EnvironmentImpl implements Environment {

    private final GameFactory factory;
    private final List<GameObject> obstacles;
    private Optional<GameObject> ball;
    private Optional<GameObject> player;
    /**
     * Build a new {@link EnvironmentImpl}.
     * 
     */
    public EnvironmentImpl() {
        this.factory = new GameFactory();
        this.obstacles = new ArrayList<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (ball.isEmpty()) {
            throw new IllegalStateException();
        }
        ball.get().updatePhysics(0, this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkCollisions() {
        // TODO Auto-generated method stub
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaticObstacle(final Point2D pos) {
        this.obstacles.add(factory.createStaticObstacle(pos));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setBall(final Point2D pos, final double radius, final Vector2D vel) {
        if (this.ball.isEmpty()) {
            this.ball = Optional.of(factory.createBall(pos, radius, vel));
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayer(final Point2D pos) {
        if (player.isEmpty()) {
            this.player = Optional.of(factory.createPlayer(pos));
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getBall() {
        return this.ball;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getPlayer() {
        return this.player;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObstacles() {
        return this.obstacles;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyBallStopped() {
        // TODO Auto-generated method stub
    }
}
