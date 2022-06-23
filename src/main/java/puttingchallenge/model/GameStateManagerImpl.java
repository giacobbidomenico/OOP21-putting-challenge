package puttingchallenge.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.Mediator;

/**
 * Implementation of {@link GameStateManager} interface.
 */
public class GameStateManagerImpl implements GameStateManager {
    private GameState currentGameState;
    private Mediator generalMediator;
    private static final GameStatus INITIAL_STATE = GameStatus.MAIN_MENU;
    private static final Map<GameStatus, GameEventType> EVENT_TO_STATUS = createMap();

    private static Map<GameStatus, GameEventType> createMap() {
        final Map<GameStatus, GameEventType> result = new HashMap<>();
        result.put(GameStatus.MAIN_MENU, GameEventType.SHOW_MAIN_MENU);
        result.put(GameStatus.LEADERBOARD, GameEventType.SHOW_LEADERBOARD);
        return Collections.unmodifiableMap(result);
    }
    /**
     * Sets the initial state of the game.
     */
    public void initState() {
        this.switchState(INITIAL_STATE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void switchState(final GameStatus status) {
        switch (status) {
            case PLAY:
                this.currentGameState = new GamePlayGameState(this, status);
                break;
            case GAME_OVER:
            case MAIN_MENU:
                this.currentGameState = new ScreenGameState(this, status);
                final GameEvent event = new GameEventImpl(EVENT_TO_STATUS.get(status));
                this.generalMediator.notifyColleagues(event, this);
                break;
        default:
            break;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getCurrentState() {
        return this.currentGameState;
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
                if (this.getCurrentState().getStatus() == GameStatus.PLAY) {
                    ((GamePlayGameState) this.getCurrentState()).shoot((Pair<Point2D, Point2D>) event.getDetails().get());
                }
                break;
            case SHOW_MAIN_MENU:
                this.switchState(GameStatus.MAIN_MENU);
                break;
            case SHOW_LEADERBOARD:
                this.switchState(GameStatus.LEADERBOARD);
                break;
            case START:
                this.switchState(GameStatus.PLAY);
                break;
            default:
                break;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Environment getCurrentEnvironment() {
        return this.getCurrentState().getEnvironment();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt) {
        this.currentGameState.getEnvironment().update(dt);
    }
}
