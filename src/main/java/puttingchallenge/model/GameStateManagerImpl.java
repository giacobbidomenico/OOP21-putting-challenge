package puttingchallenge.model;

import javafx.util.Pair;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;

/**
 * Implementation of {@link GameStateManager} interface.
 */
public class GameStateManagerImpl implements GameStateManager {
    private GameState currentGameState;
    private Mediator generalMediator;
    private static final GameStatus INITIAL_STATE = GameStatus.MAIN_MENU;
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
            case LEADERBOARD:
            case GAME_OVER:
            case MAIN_MENU:
                this.currentGameState = new ScreenGameState(this, status);
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
