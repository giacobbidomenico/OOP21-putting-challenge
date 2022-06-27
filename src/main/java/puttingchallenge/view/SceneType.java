package puttingchallenge.view;

/**
 * Enumeration that establishes the various  scenes that can be loaded.
 */
public enum SceneType {

    /**
     * The scene with the application menu.
     */
    MAIN_MENU(false),

    /**
     * The scene with the first game environment.
     */
    ENVIRONMENT1(true),

    /**
     * The scene with the second game environment.
     */
    ENVIRONMENT2(true),

    /**
     * The scene with the third game environment.
     */
    ENVIRONMENT3(true),

    /**
     * The scene with the third game environment.
     */
    LEADERBOARD(false),
 
    /**
     * The game over scene.
     */
    GAME_OVER(false),

    /**
     * The game win scene.
     */
    GAME_WIN(false);

    private final boolean isLevel;

    SceneType(final boolean isLevel) {
        this.isLevel = isLevel;
    }

    /**
     * Tells if the {@link SceneType} is a game level or an application screen.
     * 
     * @return
     *          true if the scene is a game level, false otherwise
     */
    public boolean isLevel() {
        return this.isLevel;
    }
}
