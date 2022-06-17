
package puttingchallenge.view;

import puttingchallenge.model.Environment;

/**
 * View of the application.
 */
public interface View {

    /**
     * Configure the view and show it on the screen.
     */
    void buildView();

    /**
     * Changes the current scene, read from file.
     * 
     * @param scene
     *            indicates the scene to be loaded
     * @param env
     *          the {@link Environment} of the scene to be loaded
     */
    void loadScene(SceneType scene, Environment env);

    /**
     * Renders the state of any {@link GameObject} in the current scene.
     */
    void render();
}
