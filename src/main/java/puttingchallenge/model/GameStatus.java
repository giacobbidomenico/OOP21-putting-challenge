package puttingchallenge.model;
/**
 * 
 * Enumeration for the various states of the game.
 *
 */
public enum GameStatus {
    /**
     * The player is currently aiming.
     */
    AIMING,
    /**
     * The player finally shoots the ball.
     */
    SHOOTING,
    /**
     * The player runs out of lives.
     */
    GAME_OVER,
    /**
     * The player quits the game or has just started the application.
     */
    MAIN_MENU;
}
