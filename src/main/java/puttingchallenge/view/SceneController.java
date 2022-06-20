package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject;

/**
 * Interface that defines the controller that manages the {@link Scene} of the view.
 * 
 */
public interface SceneController {

    /**
     * Initialize the scene controller.
     * 
     * @param scene
     *          the related {@link Scene}
     * @param gameObjects
     *          the list of all game objects of the current scene
     * @param controller
     *          the controller of the application
     */
    void init(Scene scene, List<GameObject> gameObjects, GameEngine controller);

    /**
     * @return the current {@link Scene} to be shown
     * 
     */
    Scene getScene();

    /**
     * @return the controller of the application
     * 
     */
    GameEngine getController();

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
