package puttingchallenge.view;

import java.util.List;
import java.util.Optional;
import javafx.scene.Scene;
import puttingchallenge.model.GameObject;

/**
 * Interface that defines the controller that manages the {@link Scene} of the view.
 * 
 */
public interface SceneController {

    /**
     * Create and configure the new main {@link Scene}.
     * 
     * @param newGameObjects
     *                   {@link GameObject} that will have to be inserted 
     *                   in the new created {@link Scene}
     */
    void setScene(List<GameObject> newGameObjects);

    /**
     * @return the current {@link Scene} to be shown.
     * 
     */
    Optional<Scene> getScene();

    /**
     * Draw the {@link GameObject} on the {@link Scene}.
     * 
     */
    void render();

}
