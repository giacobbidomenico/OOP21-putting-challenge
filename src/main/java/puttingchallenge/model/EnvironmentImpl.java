package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.events.ObservableEventsImpl;
import puttingchallenge.model.events.ObserverEvents;
import puttingchallenge.model.events.ObserverEventsImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.GameObjectImpl;
import puttingchallenge.model.gameobjects.PlayerObject;
import puttingchallenge.model.physics.BallPhysicsComponent;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.collisions.DynamicBoundingBox.CollisionTest;
import puttingchallenge.model.collisions.PassiveCircleBoundingBox;
import puttingchallenge.model.collisions.PassiveCircleBBTrajectoryBuilder;
import puttingchallenge.model.collisions.ConcretePassiveCircleBoundingBox;

/**
 * Class that implements the game environment.
 * 
 */
public class EnvironmentImpl implements Environment {
    //private static final int PERC_DISTANCE = 2;

    private Optional<ObservableEvents<ModelEventType>> observableGameState;
    private final ObservableEvents<ModelEventType> observable;
    private final ObserverEvents<ModelEventType> observer;
    private final Rectangle2D container;
    private final List<GameObject> staticObstacles;
    private final GameObject ball;
    private final PlayerObject player;
    private final GameObject hole;
    private final Point2D initPosBall;
    private final Point2D initPosPlayer;
    private boolean notidiedBallStoped;
    private boolean collisionWithHole;

    /**
     * Build a new {@link EnvironmentImpl}.
     * 
     * @param container
     *           the {@link Rectangle2D} inside which there is the game {@link Environment}
     * @param ball 
     *           the {@link GameObject} corresponding to the ball in the game {@link Environment}
     * @param player
     *           the {@link GameObject} corresponding to the player in the game {@link Environment}
     * @param staticObstacles
     *           the {@link List} of {@link GameObject}s, corresponding to the static obstacles in
     *           the game {@link Environment}
     * @param hole
     *           the {@link GameObject} corresponding to the hole in the game {@link Environment}
     */
    public EnvironmentImpl(final Rectangle2D container,
                           final GameObject ball, 
                           final PlayerObject player,
                           final List<GameObject> staticObstacles,
                           final GameObject hole) {
        this.observableGameState = Optional.empty();
        this.observable = new ObservableEventsImpl<>();
        this.observer = new ObserverEventsImpl<>();
        this.container = Objects.requireNonNull(container);
        this.ball = Objects.requireNonNull(ball);
        this.initPosBall = ball.getPosition();
        this.player = Objects.requireNonNull(player);
        this.initPosPlayer = player.getPosition();
        this.hole = Objects.requireNonNull(hole);
        this.staticObstacles = new LinkedList<>(staticObstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt) {
        final BallPhysicsComponent bf = (BallPhysicsComponent) this.ball.getPhysicsComponent();
        bf.update(dt, ball, this);
        this.receiveEvents();
        this.notifyEvents();
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
    public PlayerObject getPlayer() {
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
    public void movePlayer() {
        final BallPhysicsComponent bf = (BallPhysicsComponent) this.ball.getPhysicsComponent();
        if (this.isBallOutOfBounds()) {
            bf.setVelocity(new Vector2D(0, 0));
            this.ball.setPosition(initPosBall);
            this.player.setPosition(initPosPlayer);
            this.notidiedBallStoped = false;
            return;
        }
        this.notidiedBallStoped = false;
        final var posBall = this.ball.getPosition();
        var newPos = new Point2D(posBall.getX() + player.getWidth(), 
                                 posBall.getY() + player.getHeight());
        if (newPos.getX() >= 0) {
            player.setFlip(false);
            player.setPosition(newPos);
        }
        newPos = new Point2D(posBall.getX() - player.getWidth(), 
                             posBall.getY() - player.getHeight());
        if (newPos.getX() < this.container.getWidth()) {
            player.setFlip(true);
            player.setPosition(newPos);
        }
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
        final BallPhysicsComponent bf = (BallPhysicsComponent) this.ball.getPhysicsComponent();
        final var rectBall = new Rectangle2D(posBall.getX(), 
                                             posBall.getY(),
                                             bf.getRadius() * 2, 
                                             bf.getRadius() * 2);
        return !this.container.contains(rectBall);
    }

    private boolean isBallInTheHole() {
        return this.collisionWithHole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableEvents<ModelEventType> getObservable() {
        return this.observable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureObservable(final ObservableEvents<ModelEventType> observableGameState) {
        this.observableGameState = Optional.of(Objects.requireNonNull(observableGameState));
        this.observableGameState.get().addObserver(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvents() {
        final List<ModelEventType> events = new LinkedList<>();
        if (this.isBallStationary() && !this.notidiedBallStoped) {
            events.add(ModelEventType.BALL_STOPPED);
            this.notidiedBallStoped = true;
        }
        if (this.isBallOutOfBounds()) {
            events.add(ModelEventType.BALL_OUT_OF_BOUND);
        }
        if (this.isBallInTheHole()) {
            events.add(ModelEventType.BALL_IN_HOLE);
        }
        this.observer.sendModelEvents(events);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveEvents() {
        if (this.observableGameState.isEmpty()) {
            throw new IllegalStateException();
        }
        final List<ModelEventType> eventsReceived = this.observable.eventsRecieved();
        eventsReceived.forEach(event -> {
            switch (event) {
            case SHOOT:
                this.notidiedBallStoped = false;
                break;
            case MOVE_PLAYER:
                this.movePlayer();
                break;
            default:
                break;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
        final List<GameObject> allGameObjects = new LinkedList<>();
        allGameObjects.add(player);
        allGameObjects.add(ball);
        allGameObjects.addAll(staticObstacles);
        allGameObjects.add(hole);
        return allGameObjects;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalAccessException 
     */
    @Override
    public Optional<CollisionTest> checkCollisions(final PassiveCircleBoundingBox ballHitbox, 
            final BallPhysicsComponent ballPhysics,
            final Point2D ballPosition)  {

        final PassiveCircleBBTrajectoryBuilder builder = new PassiveCircleBBTrajectoryBuilder();
        final PassiveCircleBoundingBox box = new ConcretePassiveCircleBoundingBox(
                new Point2D(ballPosition.getX() + ballHitbox.getRadius(), 
                        ballPosition.getY() + ballHitbox.getRadius()), 
                ballHitbox.getRadius());

        builder.setHitbox(box);
        builder.setPhysic(ballPhysics);
        builder.setPosition(box.getPosition());

        CollisionTest result = ((GameObjectImpl) this.hole).getHitBox().collidesWith(builder);
        if (result.isCollisionOccurred()) {
            this.collisionWithHole = true;
        }

        result = null;
        for (final GameObject gameObject : staticObstacles) {
            final CollisionTest currentResult = ((GameObjectImpl) gameObject).getHitBox().collidesWith(builder);
            if (currentResult.isCollisionOccurred()) {
                result = currentResult;
            }
        }
        return Optional.ofNullable(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(ball, collisionWithHole, container, hole, initPosBall, initPosPlayer, notidiedBallStoped,
                observable, observableGameState, observer, player, staticObstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Environment) {
            final Environment env = (Environment) obj;
            return ball.equals(env.getBall()) 
                   && staticObstacles.containsAll(env.getStaticObstacles())
                   && container.equals(env.getContainer()) 
                   && hole.equals(env.getHole());
        }
        return false;
    }

}
