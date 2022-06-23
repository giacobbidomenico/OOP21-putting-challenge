package puttingchallenge.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.util.Pair;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.events.ObservableEventsImpl;
import puttingchallenge.model.events.ObserverEvents;
import puttingchallenge.model.events.ObserverEventsImpl;
import puttingchallenge.view.SceneType;

/**
 * Class that represent the in-game {@link GameState}.
 *
 */
public class GamePlayGameState extends AbstractGameState {
    private int score;
    private int lives;
    private ObservableEvents<ModelEventType> environmentObservable;
    private ObservableEvents<ModelEventType> observable;
    private ObserverEvents<ModelEventType> observer;
    private static final int NO_LIVES = 0;
    private static final int NO_SCORE = 0;
    private static final int MAX_LIVES = 3;
    private static final Iterator<SceneType> MAPS = Collections.unmodifiableList(Arrays.asList(SceneType.ENVIRONMENT1, SceneType.ENVIRONMENT2, SceneType.ENVIRONMENT3)).iterator();
    /**
     * 
     * @param manager
     * @param status
     */
    public GamePlayGameState(final GameStateManager manager, final GameStatus status) {
        super(manager, status);
    }
    /**
     * {@inheritDoc}
     */
    public void initState() {
        this.lives = MAX_LIVES;
        this.score = NO_SCORE;
        this.loadNextEnvironment();
    }
    private void loadNextEnvironment() {
        try {
            EnvironmentLoader.getLoader().getEnvironment(MAPS.next());
        } catch (NoSuchElementException e) {
            this.leavingState(GameStatus.GAME_OVER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.environmentObservable = this.getEnvironment().getObservable();
        this.observer = new ObserverEventsImpl<>();
        this.environmentObservable.addObserver(this.observer);
        this.observable = new ObservableEventsImpl<>();
        this.getEnvironment().configureObservable(this.observable);
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
        if (this.lives == NO_LIVES) {
            this.leavingState(GameStatus.GAME_OVER);
        } else {
            this.notifyEvents(ModelEventType.MOVE_PLAYER);
        }
    }
    /**
     * 
     * @param points
     */
    public void shoot(final Pair<Point2D, Point2D> points) {
        final Vector2D shootingVector = Vector2D.getVectorFrom(points.getKey(), points.getValue());
        shootingVector.flipVector();
        this.getEnvironment().getBall().setVelocity(shootingVector);
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
    void notifyEvents(final ModelEventType eventType) {
        this.observer.notifyEvents(Collections.unmodifiableList(Arrays.asList(eventType)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    void receiveEvents() {
        final List<ModelEventType> eventsReceived = this.environmentObservable.eventsRecieved();
        eventsReceived.stream().forEach((event) -> {
            switch (event) {
            case BALL_IN_HOLE:
                this.handleWin();
                break;
            case BALL_OUT_OF_BOUND:
            case BALL_STOPPED:
                this.handleMiss();
                break;
            default:
                break;
            }
        });

    }
}
