package puttingchallenge.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.GameEventWithDetailsImpl;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.SceneType;

/**
 * Implementation of {@link GameStateManager} interface.
 */
public class GameStateManagerImpl implements GameStateManager {
    private GameState currentGameState;
    private Mediator generalMediator;
    private static final GameStatus INITIAL_STATE = GameStatus.MAIN_MENU;
    private static final Map<GameStatus, GameEventType> EVENT_TO_STATUS = createMap();

    private static Map<GameStatus, GameEventType> createMap() {
        final Map<GameStatus, GameEventType> associativeMap = new HashMap<>();
        associativeMap.put(GameStatus.MAIN_MENU, GameEventType.SHOW_MAIN_MENU);
        associativeMap.put(GameStatus.LEADERBOARD, GameEventType.SHOW_LEADERBOARD);
        associativeMap.put(GameStatus.GAME_WIN, GameEventType.WIN);
        associativeMap.put(GameStatus.GAME_OVER, GameEventType.GAMEOVER);
        return Collections.unmodifiableMap(associativeMap);
    }
    /**
     * {@inheritDoc}
     */
    public void initState() {
        this.currentGameState = new ScreenGameState(this, INITIAL_STATE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void switchState(final GameStatus status) {
        switch (status) {
            case PLAY:
                this.currentGameState = new GamePlayGameState(this, status);
                final Pair<SceneType, List<GameObject>> pair = this.currentGameState.initState();
                this.generalMediator.notifyColleagues(new GameEventWithDetailsImpl<Pair<SceneType, List<GameObject>>>(GameEventType.SET_SCENE, pair), this);
                break;
            case GAME_OVER:
            case GAME_WIN:
                this.currentGameState = new ScreenGameState(this, status);
                final GameEvent event = new GameEventImpl(EVENT_TO_STATUS.get(status));
                this.generalMediator.notifyColleagues(event, this);
                break;
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
                final GameEvent mainMenuEvent = new GameEventWithDetailsImpl<>(GameEventType.SET_SCENE,
                                                                                  new Pair<>(SceneType.MAIN_MENU,
                                                                                             Collections.emptyList()));
                this.generalMediator.notifyColleagues(mainMenuEvent, this);
                break;
            case SHOW_LEADERBOARD:
                this.switchState(GameStatus.LEADERBOARD);
                final GameEvent leaderboardEvent = new GameEventWithDetailsImpl<>(GameEventType.SET_SCENE,
                                                                                  new Pair<>(SceneType.LEADEARBOARD,
                                                                                             Collections.emptyList()));
                this.generalMediator.notifyColleagues(leaderboardEvent, this);
                break;
            case START:
                this.switchState(GameStatus.PLAY);
                break;
            case WIN:
                final GameEvent winEvent = new GameEventWithDetailsImpl<>(GameEventType.SET_SCENE,
                                                                          new Pair<>(SceneType.GAME_WIN,
                                                                                     Collections.emptyList()));
                this.generalMediator.notifyColleagues(winEvent, this);
                break;
            case GAMEOVER:
                final GameEvent gameOverEvent = new GameEventWithDetailsImpl<>(GameEventType.SET_SCENE,
                                                                               new Pair<>(SceneType.GAME_OVER,
                                                                                          this.getCurrentEnvironment().get().getObjects()));
                this.generalMediator.notifyColleagues(gameOverEvent, this);
                break;
            default:
                break;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Environment> getCurrentEnvironment() {
        return this.getCurrentState().getEnvironment();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long dt) {
        if (this.currentGameState.getEnvironment().isPresent()) {
            this.currentGameState.getEnvironment().get().update(dt);
            this.currentGameState.receiveEvents();
        }
    }
}
