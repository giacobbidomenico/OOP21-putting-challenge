package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.events.ObservableEventsImpl;
import puttingchallenge.model.events.ObserverEvents;
import puttingchallenge.model.events.ObserverEventsImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.events.ModelEventType;

/**
 * Class that implements the game environment.
 * 
 */
public class EnvironmentImpl implements Environment {
    private Optional<ObservableEvents> observableGameState;
    private final ObservableEvents observable;
    private final ObserverEvents observer;
    private final Rectangle2D container;
    private final List<GameObject> staticObstacles;
    private final GameObject ball;
    private final GameObject player;
    private final GameObject hole;


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
        this.observable = new ObservableEventsImpl();
        this.observer = new ObserverEventsImpl();
        this.container = Objects.requireNonNull(container);
        this.ball = Objects.requireNonNull(ball);
        this.player = Objects.requireNonNull(player);
        this.hole = Objects.requireNonNull(hole);
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

    private boolean isBallInTheHole() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableEvents getObservable() {
        return this.observable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureObservable(final ObservableEvents observableGameState) {
        this.observableGameState = Optional.of(Objects.requireNonNull(observableGameState));
        this.observableGameState.get().addObserver(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvents() {
        final List<ModelEventType> events = new LinkedList<>();
        if (this.isBallOutOfBounds()) {
            events.add(ModelEventType.BALL_STOPPED);
        }
        if (this.isBallOutOfBounds()) {
            events.add(ModelEventType.BALL_OUT_OF_BOUND);
        }
        if (this.isBallInTheHole()) {
            events.add(ModelEventType.BALL_IN_HOLE);
        }
        this.observer.notifyEvents(events);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveEvents() {
        if (this.observableGameState.isEmpty()) {
            throw new IllegalStateException();
        }
        final List<ModelEventType> eventsReceived = this.observableGameState.get().eventsRecieved();
        if (eventsReceived.stream()
                .filter(e -> e.equals(ModelEventType.MOVE_PLAYER))
                .count() != 0) {
            this.movePlayer();
        }
    }

}
