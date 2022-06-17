
package puttingchallenge.view;

import java.util.List;

import puttingchallenge.graphics.GraphicComponent;

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
     * @param objs
     *          a {@link List} containing the graphic components of the current level's game objects 
     */
    void loadScene(SceneType scene, List<GraphicComponent> objs);

    /**
     * Renders the state of any {@link GameObject} in the current scene.
     */
    void render();
}
