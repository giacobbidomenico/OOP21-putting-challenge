package puttingchallenge;

import javafx.application.Application;
import javafx.stage.Stage;
import puttingchallenge.core.GameLoopImpl;

/**
 * Class that loads the graphics part of the application.
 */
public class ApplicationLoad extends Application {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GameLoopImpl gameEngine = new GameLoopImpl(primaryStage);
        gameEngine.start();
    }

}
