package puttingchallenge.core;


import javafx.stage.Stage;
import puttingchallenge.model.GameStateManager;
import puttingchallenge.model.GameStateManagerImpl;
import puttingchallenge.model.events.Colleague;
import puttingchallenge.model.events.ConcreteMediator;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.view.View;
import puttingchallenge.view.ViewImpl;

/**
 * Controller of the entire application.
 */
public class GameLoopImpl implements GameEngine, Colleague {

    private static final long FRAME_TIME = 20;
    private Mediator mediator;
    private final GameStateManager gameState;
    private final View view;

    /**
     * Builds a {@link GameLoopImpl}.
     * @param primaryStage main stage of the application
     */
    public GameLoopImpl(final Stage primaryStage) {
        final Mediator mediator = new ConcreteMediator();
        this.gameState = new GameStateManagerImpl();
        this.view = new ViewImpl(primaryStage);

        mediator.addColleague(gameState);
        mediator.addColleague(view);
        mediator.addColleague(this);

        this.gameState.setMediator(mediator);
        this.view.setMediator(mediator);
        this.setMediator(mediator);
    }

    private void waitCycleTime(final long startTime) {
        long delta = System.currentTimeMillis() - startTime;
        if (delta < FRAME_TIME) {
            try {
                Thread.sleep(FRAME_TIME - delta);
            } catch (Exception ex) { }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameLoop() {
        long previusCycleStartTime = System.currentTimeMillis();
        while (true) {
            long currentCycleStartTime = System.currentTimeMillis();
            long delta = currentCycleStartTime - previusCycleStartTime;
            waitCycleTime(currentCycleStartTime);
            previusCycleStartTime = System.currentTimeMillis();
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
        // TODO Auto-generated method stub
    }

}
