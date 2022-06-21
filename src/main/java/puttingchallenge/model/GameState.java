package puttingchallenge.model;

import puttingchallenge.common.Point2D;

/**
 * 
 * Interface for maintaining and handling the state of the game.
 *
 */
public interface GameState {
    /**
     *
     */
    int NO_LIVES = 0;
    /**
     * @return
     *          the current status of the game
     */
    GameStatus getStatus();
    /**
     * Get the current environment.
     * @return
     *      the current environment
     */
    Environment getEnvironment();
    /**
     * The player starts aiming.
     * @param pressedOnPoint
     */
    void aim(Point2D pressedOnPoint);
    /**
     * The player finally shoots the ball.
     * @param releasedOnPoint
     */
    void shoot(Point2D releasedOnPoint);
}
