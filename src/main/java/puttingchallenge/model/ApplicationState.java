package puttingchallenge.model;

/**
 * Enumration that establishes the various types of events occurred in the Application.
 */
public enum ApplicationState {

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
     * Viewing the results of ended matches.
     */
    SHOW_RESULTS,

    /**
     * Main menu required.
     */
    MAIN_MENU;
}
