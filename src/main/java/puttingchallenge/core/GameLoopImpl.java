package puttingchallenge.core;

import java.util.LinkedList;
import java.util.Queue;

import javafx.application.Platform;
import javafx.stage.Stage;
import puttingchallenge.model.GameStateManager;
import puttingchallenge.model.GameStateManagerImpl;
import puttingchallenge.model.events.Colleague;
import puttingchallenge.model.events.ConcreteMediator;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.GameEventWithDetailsImpl;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.view.SceneType;
import puttingchallenge.view.View;
import puttingchallenge.view.ViewImpl;

/**
 * Controller of the entire application.
 */
public class GameLoopImpl extends Thread implements GameEngine, Colleague {

    private static final long FRAME_TIME = 20;
    private Mediator mediator;
    private final GameStateManager gameState;
    private final View view;
    private final Queue<GameEvent> receivedEvents;
    private boolean isOver;

    /**
     * Builds a {@link GameLoopImpl}.
     * 
     * @param primaryStage
     *          main stage of the application
     */
    public GameLoopImpl(final Stage primaryStage) {
        this.gameState = new GameStateManagerImpl();
        this.view = new ViewImpl(primaryStage);
        this.receivedEvents = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.launch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launch() {
        this.isOver = false;

        final Mediator mediator = new ConcreteMediator();
        mediator.addColleague(gameState);
        mediator.addColleague(view);
        mediator.addColleague(this);

        this.gameState.setMediator(mediator);
        this.view.setMediator(mediator);
        this.setMediator(mediator);

        this.gameState.initState();
        this.view.buildView();

        this.gameLoop();
    }

    private void waitCycleTime(final long startTime) {
        final long delta = System.currentTimeMillis() - startTime;
        if (delta < FRAME_TIME) {
            try {
                Thread.sleep(FRAME_TIME - delta);
            } catch (Exception ex) { }
        }
    }

    private void updateGame(final long time) {
        this.gameState.update(time);
    }

    private void render() {
        view.render();
    }

    private void processInput() {
        while (this.receivedEvents.size() > 0) {
            final GameEvent event = receivedEvents.poll();
            switch (event.getEventType()) {
                case QUIT:
                    this.isOver = true;
                    Platform.exit();
                    break;
                case WIN:
                    final GameEvent winEvent = new GameEventWithDetailsImpl<>(GameEventType.WIN, SceneType.GAME_WIN);
                    this.mediator.notifyColleagues(winEvent, this);
                    break;
                case GAMEOVER:
                    final GameEvent gameOverEvent = new GameEventWithDetailsImpl<>(GameEventType.GAMEOVER, SceneType.GAME_OVER);
                    this.mediator.notifyColleagues(gameOverEvent, this);
                    break;
                case SHOW_LEADERBOARD:
                    final GameEvent leaderboardEvent = new GameEventWithDetailsImpl<>(GameEventType.SHOW_LEADERBOARD, SceneType.LEADEARBOARD);
                    this.mediator.notifyColleagues(leaderboardEvent, this);
                    break;
                case SHOW_MAIN_MENU:
                    final GameEvent mainMenuEvent = new GameEventWithDetailsImpl<>(GameEventType.SHOW_MAIN_MENU, SceneType.MAIN_MENU);
                    this.mediator.notifyColleagues(mainMenuEvent, this);
                    break;
                default: 
                    break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameLoop() {
        long previusCycleStartTime = System.currentTimeMillis();
        while (!this.isOver) {
            final long currentCycleStartTime = System.currentTimeMillis();
            final long delta = currentCycleStartTime - previusCycleStartTime;

            this.processInput();
            this.updateGame(delta);
            this.render();

            waitCycleTime(currentCycleStartTime);
            previusCycleStartTime = currentCycleStartTime;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
        this.mediator = mediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent event) {
        this.receivedEvents.add(event);
    }

}
