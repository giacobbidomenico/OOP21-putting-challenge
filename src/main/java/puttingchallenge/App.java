package puttingchallenge;

import javafx.application.Application;

/**
 * The class that start the application.
 */
public final class App {
    private App() {
    }

    /**
     * Main class.
     * 
     * @param args
     *          not used
     */
    public static void main(final String... args) {
        Application.launch(ApplicationLoad.class, args);
    }
}
