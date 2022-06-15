
package puttingchallenge.view;

/**
 * View of the application.
 */
public interface View {

    /**
     * Sets the view configuration.
     * 
     */
    void launch();
    /**
     * Changes the current scene shown.
     * 
     * @param scene
     *            scene that needs to be loaded
     */
    void loadScene(SceneType scene);
    /**
     * Renders the state of the current scene.
     * 
     */
    void render();

}
