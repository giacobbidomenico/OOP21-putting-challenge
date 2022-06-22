package puttingchallenge.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.events.ObservableEventsImpl;
import puttingchallenge.model.events.ObserverEvents;
import puttingchallenge.model.events.ObserverEventsImpl;
import puttingchallenge.model.events.GameEvent;

/**
 * Class that implements the game state.
 */
public class GameStateImpl implements GameState {
    private int score;
    private int lives;
    private Environment currentEnvironment;
    private GameStatus status;
    private Point2D startingPoint;
    private Mediator generalMediator;
    private Mediator environmentMediator;
    private Optional<ObservableEvents> observableEnvironment;
    private final ObservableEvents observable;
    private final ObserverEvents observer;

    public GameStateImpl() {
        this.observer = new ObserverEventsImpl();
        this.observable = new ObservableEventsImpl();
        this.observableEnvironment = Optional.empty();
        this.environmentMediator = new Mediator();
        this.setMediator(this.environmentMediator);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameStatus getStatus() {
        return this.status;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Environment getEnvironment() {
        return this.currentEnvironment;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void aim(final Point2D pressedOnPoint) {
        this.startingPoint = pressedOnPoint;
        this.setStatus(GameStatus.AIMING);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot(final Point2D releasedOnPoint) {
        this.getEnvironment().getBall().setVelocity(Vector2D.getVectorFrom(releasedOnPoint, this.startingPoint));
        this.setStatus(GameStatus.SHOOTING);
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
        if (this.lives == NO_LIVES) {
            this.setStatus(GameStatus.GAME_OVER);
            // game engine should be notified here
        }
    }
    /**
     * Increments lives due to in game boosts.
     */
    private void incLives() {
        this.lives++;
    }
    /**
     * Sets the appropriate environment for the specific state.
     */

    private void setNextEnvironment() {
        // TODO
        // remove the previous environment
        this.environmentMediator.addColleague(this.currentEnvironment);
        this.currentEnvironment.setMediator(this.environmentMediator);
        this.currentEnvironment.configureObservable(this.observable);
        this.observableEnvironment = Optional.of(Objects.requireNonNull(this.currentEnvironment.getObservable()));
    }

    private void notifyEventToEnvironment() {
        final List<ModelEventType> events = new LinkedList<>();
        //TODO
        this.observer.notifyEvents(events);
    }

    private void receiveEventFromEnvironment() {
        if (this.observableEnvironment.isEmpty()) {
            throw new IllegalStateException();
        }
        final List<ModelEventType> receivedEvents = this.observableEnvironment.get().eventsRecieved();
        receivedEvents.stream().peek(e -> {
            switch (e) {
            case BALL_IN_HOLE:
                //TODO
                break;
            case BALL_OUT_OF_BOUND:
                //TODO
                break;
            case BALL_STOPPED:
                //TODO
                break;
            default:
                break;
            }
        });
    }

    /**
     * Update the status of the game.
     * @param status
     */
    private void setStatus(final GameStatus status) {
        this.status = status;
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
    public void notifyEvent(final GameEvent<?, ?> event) {
        //TODO
    }

    public ObservableEvents getObservable() {
        return this.observable;
    }
}
