
package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * Class that implements the game environment.
 * 
 */
public class EnvironmentImpl implements Environment {
    private final List<GameObject> staticObstacles;
    private final GameObject ball;
    private final GameObject player;
    /**
     * Build a new {@link EnvironmentImpl}.
     * 
     * @param ball 
     *           the {@link GameObject} corresponding to the ball in the game environment
     * @param player
     *           the {@link GameObject} corresponding to the player in the game environment
     */
    public EnvironmentImpl(final GameObject ball, final GameObject player) {
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
}
