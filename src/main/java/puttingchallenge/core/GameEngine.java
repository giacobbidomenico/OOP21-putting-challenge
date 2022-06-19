package puttingchallenge.core;

import puttingchallenge.model.WorldEventListener;

/**
 * Represent a basic engine for the game.
 */
public interface GameEngine extends WorldEventListener {

    /**
     * Defines a time window within which to process inputs, update internal state and render graphics.
     */
    void gameLoop();

}
