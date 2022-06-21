package puttingchallenge;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.stage.Screen;
import puttingchallenge.core.GameEngine;

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
        final Rectangle2D screenDim = Screen.getPrimary().getBounds();
        final GameEngine gameEngine = new GameEngine(primaryStage, screenDim);
    }
}
