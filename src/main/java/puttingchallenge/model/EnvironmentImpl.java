package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Vector2D;
<<<<<<< HEAD
import puttingchallenge.gameobjects.GameObject;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.ModelEventType;
=======
import puttingchallenge.model.gameobjects.GameObject;
>>>>>>> master

/**
 * Class that implements the game environment.
 * 
 */
public class EnvironmentImpl implements Environment {
    private final Rectangle2D container;
    private final List<GameObject> staticObstacles;
    private final GameObject ball;
    private final GameObject player;
    private final GameObject hole;
    private Optional<Mediator> mediator;

    /**
     * Build a new {@link EnvironmentImpl}.
     * 
     * @param container
     *           the {@link Rectangle2D} inside which there is the game {@link Environment}
     * @param ball 
     *           the {@link GameObject} corresponding to the ball in the game {@link Environment}
     * @param player
     *           the {@link GameObject} corresponding to the player in the game {@link Environment}
     * @param hole
     *           the {@link GameObject} corresponding to the hole in the game {@link Environment}
     */
    public EnvironmentImpl(final Rectangle2D container,
                           final GameObject ball, 
                           final GameObject player,
                           final GameObject hole) {
        this.container = Objects.requireNonNull(container);
        this.ball = Objects.requireNonNull(ball);
        this.player = Objects.requireNonNull(player);
        this.hole = Objects.requireNonNull(hole);
        this.mediator = Optional.empty();
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
    public GameObject getHole() {
        return this.hole;
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
    public Rectangle2D getContainer() {
        return this.container;
    }

    /**
     * @return true if the ball is stationary, 
     *         false otherwise
     */
    private boolean isBallStationary() {
        return this.ball.getVelocity().equals(new Vector2D(0, 0));
    }

    /**
     * @return true if the ball is out of bounds
     *         false otherwise
     */
    private boolean isBallOutOfBounds() {
        final var posBall = this.ball.getPosition();
        final var rectBall = new Rectangle2D(posBall.getX(), 
                                             posBall.getY(), 
                                             ball.getPhysicsComponent().getRadius() * 2, 
                                             ball.getPhysicsComponent().getRadius() * 2);
        return this.container.contains(rectBall);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
        this.mediator = Optional.of(mediator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent<?> event) {
        if (this.mediator.isEmpty()) {
            throw new IllegalStateException();
        }
        this.mediator.get().notifyColleagues(event, this);
    }
}
