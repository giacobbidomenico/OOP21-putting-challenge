package puttingchallenge.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.GameEventWithDetailsImpl;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.events.ObservableEventsImpl;
import puttingchallenge.model.events.ObserverEvents;
import puttingchallenge.model.events.ObserverEventsImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.physics.BallPhysicsComponent;
import puttingchallenge.view.SceneType;

/**
 * Class that represent the in-game {@link GameState}.
 */
public class GamePlayGameState extends AbstractGameState {

    private static final int NONE = 0;
    private static final int MAX_LIVES = 3;

    private int score;
    private int lives;
    private ObservableEvents<ModelEventType> environmentObservable;
    private ObservableEvents<ModelEventType> observable;
    private ObserverEvents<ModelEventType> observer;
    private final Iterator<SceneType> maps = Collections.unmodifiableList(Arrays.asList(SceneType.ENVIRONMENT1, SceneType.ENVIRONMENT2, SceneType.ENVIRONMENT3)).iterator();
    private SceneType currentScene;
    private Mediator generalMediator;
    private int nShoots;

    /**
     * Build a new {@link GamePlayGameState} object.
     * @param manager
     *          of the state
     * @param status
     *          associated with the {@link GamePlayGameState} state
     */
    public GamePlayGameState(final GameStateManager manager, final GameStatus status) {
        super(manager, status);
    }
    /**
     * {@inheritDoc}
     */
    public Pair<SceneType, List<GameObject>> initState() {
        this.lives = MAX_LIVES;
        this.score = NONE;
        this.loadNextEnvironment();
        return new Pair<SceneType, List<GameObject>>(this.currentScene, this.getEnvironment().get().getObjects());
    }

    private void initModelComunication() {
        this.environmentObservable = this.getEnvironment().get().getObservable();
        this.observer = new ObserverEventsImpl<>();
        this.environmentObservable.addObserver(this.observer);
        this.observable = new ObservableEventsImpl<>();
        this.getEnvironment().get().configureObservable(this.observable);
    }
    /**
     * Sets the next {@link Environment} according to the map list.
     */
    private void loadNextEnvironment() {
        try {
            if (maps.hasNext()) {
                this.currentScene = maps.next();
                this.setEnvironment(EnvironmentLoader.getLoader().getEnvironment(currentScene));
                this.initModelComunication();
                this.generalMediator.notifyColleagues(new GameEventWithDetailsImpl<>(GameEventType.SET_SCENE, new Pair<SceneType, List<GameObject>>(this.currentScene, getEnvironment().get().getObjects())), this);
                this.generalMediator.notifyColleagues(new GameEventWithDetailsImpl<Pair<Integer, Integer>>(GameEventType.UPDATE_STATS, new Pair<Integer, Integer>(this.getLives(), this.getScore())), this);
                this.nShoots = NONE;
            } else {
                this.leavingState(GameStatus.GAME_WIN);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the environment exists.
     */
    private void checkExceptionEnvironment() {
        if (this.getEnvironment().isEmpty()) {
            throw new IllegalStateException();
        }
    }
    /**
     * Decrements the game score.
     * Note that in game score could become negative in case the player takes penalties
     */
    private void decScore() {
        this.score--;
    }
    /**
     * Increments the game score.
     */
    private void incScore() {
        this.score++;
    }
    /**
     * Decrements lives.
     */
    private void decLives() {
        this.lives--;
    }
    /**
     * Increments lives due to in game boosts.
     */
    private void incLives() {
        this.lives++;
    }
    /**
     * Method called when the ball enters the hole.
     */
    private void handleWin() {
        this.incScore();
        this.loadNextEnvironment();
    }
    /**
     * Method called when the ball stops or it is out of bound.
     */
    private void handleMiss() {
        this.decLives();
        if (this.lives == NONE) {
            this.leavingState(GameStatus.GAME_OVER);
        } else {
            this.generalMediator.notifyColleagues(new GameEventWithDetailsImpl<Pair<Integer, Integer>>(GameEventType.UPDATE_STATS, new Pair<Integer, Integer>(this.getLives(), this.getScore())), this);
            this.notifyEvents(ModelEventType.MOVE_PLAYER);
        }
    }
    /**
     * Sets the velocity of the ball according to the aiming {@link Vector2D} created from the the {@link Point2D}s.
     * @param points
     *          where the mouse was pressed and released during the aiming phase
     */
    public void shoot(final Pair<Point2D, Point2D> points) {
        final BallPhysicsComponent ballPhysicsComponent = (BallPhysicsComponent) this.getEnvironment().get().getBall().getPhysicsComponent();
        if (!ballPhysicsComponent.isMoving()) {
            final Vector2D shootingVector = Vector2D.getVectorFrom(points.getKey(), points.getValue());
            //this.checkExceptionEnvironment();
            this.getEnvironment().get().getBall().setVelocity(shootingVector);
            this.nShoots++;
            this.notifyEvents(ModelEventType.SHOOT);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    void leavingState(final GameStatus nextStatus) {
        // write on file
        super.leavingState(nextStatus);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvents(final ModelEventType eventType) {
        System.out.println(eventType);
        this.observer.sendModelEvents(Collections.unmodifiableList(Arrays.asList(eventType)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveEvents() {
        final List<ModelEventType> eventsReceived = this.observable.eventsRecieved();
        if (!eventsReceived.isEmpty()) {
            eventsReceived.stream().forEach((event) -> {
                switch (event) {
                case BALL_IN_HOLE:
                    this.handleWin();
                    break;
                case BALL_STOPPED:
                    if (nShoots == 0) {
                        break;
                    }
                case BALL_OUT_OF_BOUND:
                    this.handleMiss();
                    break;
                default:
                    break;
                }
            });
        }
    }
    /**
     * @return
     *          the score of the current game
     */
    public int getScore() {
        return score;
    }
    /**
     * @return
     *          the remaining lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
        this.generalMediator = mediator;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent event) {
        switch (event.getEventType()) {
            case SHOOT:
                if (this.getStatus() == GameStatus.PLAY) {
                    final Pair<Point2D, Point2D> points = (Pair<Point2D, Point2D>) event.getDetails().get();
                    this.shoot(points);
                }
                break;
        default:
            break;
        }
    }
}
