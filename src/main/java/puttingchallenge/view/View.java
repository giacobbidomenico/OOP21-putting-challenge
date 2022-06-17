
package puttingchallenge.view;


/**
 * View of the application.
 */
public interface View {
    /**
     * Configure the view and show it on the screen.
     * 
     */
    void buildView();

    /**
     * Changes the current scene shown.
     * 
     * @param scene
     *            scene that needs to be loaded
     */
    void loadScene(SceneType scene);

    /**
     * Renders the state of the {@link GameObject} in the current scene.
     * 
     */
    void render();
}
