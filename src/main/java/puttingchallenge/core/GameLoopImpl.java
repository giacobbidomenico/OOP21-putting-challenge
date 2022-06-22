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

    private Mediator mediator;
    private GameStateManager gameState;
    private View view;

    /**
     * Builds a {@link GameLoopImpl}.
     * @param primaryStage main stage of the application
     */
    public GameLoopImpl(final Stage primaryStage) {
        Mediator mediator = new ConcreteMediator();
        this.gameState = new GameStateManagerImpl();
        this.view = new ViewImpl(primaryStage);

        mediator.addColleague(gameState);
        mediator.addColleague(view);
        mediator.addColleague(this);

        this.gameState.setMediator(mediator);
        this.view.setMediator(mediator);
        this.setMediator(mediator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameLoop() {
        while (true) {

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
