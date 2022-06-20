package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * Class that implements the game environment.
 * 
 */
public class EnvironmentImpl implements Environment {
    private final double percWidth;
    private final double percHeight;
    private final List<GameObject> staticObstacles;
    private final GameObject ball;
    private final GameObject player;

    /**
     * Build a new {@link EnvironmentImpl}.
     * 
     * @param percWidth
     *                width of the game environment in percent
     * @param percHeight
     *                height of the game environment in percent
     * @param ball 
     *           the {@link GameObject} corresponding to the ball in the game environment
     * @param player
     *           the {@link GameObject} corresponding to the player in the game environment
     */
    public EnvironmentImpl(final double percWidth,
                           final double percHeight,
                           final GameObject ball, 
                           final GameObject player) {
        this.percWidth = Objects.requireNonNull(percWidth);
        this.percHeight = Objects.requireNonNull(percHeight);
        this.ball = Objects.requireNonNull(ball);
        this.player = Objects.requireNonNull(player);
        this.staticObstacles = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        ball.updatePhysics(0, this);
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
    public void addStaticObstacle(final GameObject obstacle) {
        this.staticObstacles.add(Objects.requireNonNull(obstacle));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getBall() {
        return this.ball;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getPlayer() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getStaticObstacles() {
        return this.staticObstacles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyBallStopped() {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer() {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPercWidth() {
        return this.percWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPercHeight() {
        return this.percHeight;
    }
}
