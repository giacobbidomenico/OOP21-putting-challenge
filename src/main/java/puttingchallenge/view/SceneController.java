package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.model.GameObject;

/**
 * Interface that defines the controller that manages the {@link Scene} of the view.
 * 
 */
public interface SceneController {

    /**
     * @return the current {@link Scene} to be shown
     * 
     */
    Scene getScene();

    /**
     * @return the {@link View} where the {@link Scene} will be displayed
     * 
     */
    View getView();

    /**
     * @return the list of {@link GameObject} present in the current {@link Scene}
     * 
     */
    List<GameObject> getGameObjects();

    /**
     * Draw the {@link GameObject} on the {@link Scene}.
     * 
     */
    void render();
}
