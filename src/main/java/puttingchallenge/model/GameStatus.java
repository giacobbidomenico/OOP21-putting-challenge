package puttingchallenge.model;
/**
 * 
 * Enumeration for the various states of the game.
 *
 */
public enum GameStatus {
    /**
     * The player started the game.
     */
    PLAY,
    /**
     * The player runs out of lives.
     */
    GAME_OVER,
    /**
     * The player won every single map in the game.
     */
    GAME_WIN,
    /**
     * The user consults the leaderboard.
     */
    LEADERBOARD,
    /**
     * The player quits the game or has just started the application.
     */
    MAIN_MENU;
}
