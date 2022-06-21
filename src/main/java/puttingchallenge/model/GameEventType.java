package puttingchallenge.model;

/**
 * Enumeration that establishes the various types of events occurred in the Application, to exchange between
 * Model, Controller and View.
 */
public enum GameEventType {

    /**
     * A match has started.
     */
    START,

    /**
     * A match has end.
     */
    GAMEOVER,

    /**
     * A match has been won.
     */
    WIN,

    /**
     * A match is proceeding.
     */
    AIM,

    /**
     * Leaderboard required.
     */
    SHOW_LEADERBOARD,

    /**
     * Main menu required.
     */
    SHOW_MAIN_MENU;
}
