
package puttingchallenge.view;

import puttingchallenge.model.events.Colleague;

/**
 * View of the application.
 */
public interface View extends Colleague {

    /**
     * Configure the view and show it on the screen.
     */
    void buildView();

    /**
     * Changes the current scene, read from file.
     * 
     * @param scene
     *            indicates the scene to be loaded
     */
    void loadScene(SceneType scene);

    /**
     * Renders the state of any {@link GameObject} in the current scene.
     */
    void render();
}
