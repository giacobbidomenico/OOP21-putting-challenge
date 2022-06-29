package puttingchallenge.model.events;

/**
 * Enumeration that establishes the various types of events occurred in the Application, to exchange between
 * Model, Controller and View.
 */
public enum GameEventType {

    /**
     * Require to the application the start of the game.
     */
    START,

    /**
     * Notify the application that the game is over.
     */
    GAMEOVER,

    /**
     * Notify that the player shot the ball.
     */
    SHOOT,

    /**
     * Notify the application that the game is won.
     */
    WIN,

    /**
     * Require to the application to show the leaderboard.
     */
    SHOW_LEADERBOARD,

    /**
     * Require to the application to show the main menu.
     */
    SHOW_MAIN_MENU,

    /**
     * Require to set a specified scene.
     */
    SET_SCENE,

    /**
     * Require to update the game statistics (lives and score).
     */
    UPDATE_STATS,

    /**
     * Require to shutdown the entire application.
     */
    QUIT;
}
