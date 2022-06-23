package puttingchallenge.core;

/**
 * Represent a basic engine for the game.
 */
public interface GameEngine {

    /**
     * Defines a time window within which to process inputs, update internal state and render graphics.
     */
    void gameLoop();

    /**
     * Launch the entire application.
     */
    void launch();

}
