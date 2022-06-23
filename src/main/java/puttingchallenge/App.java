package puttingchallenge;

import javafx.application.Application;
import javafx.stage.Stage;
import puttingchallenge.core.GameEngine;
import puttingchallenge.core.GameLoopImpl;

/**
 * The class that start the application.
 */
public final class App extends Application {

    /**
     * The entry point of the application.
     * 
     * @param args
     *          unused
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GameEngine gameEngine = new GameLoopImpl(primaryStage);
        gameEngine.launch();
    }
}
