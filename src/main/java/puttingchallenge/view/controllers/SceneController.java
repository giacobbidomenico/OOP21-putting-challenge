package puttingchallenge.view.controllers;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.model.events.Colleague;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Interface that defines the controller that manages the {@link Scene} of the view.
 * 
 */
public interface SceneController extends Colleague {

    /**
     * Initialize the scene controller.
     * 
     * @param scene
     *          the related {@link Scene}
     * @param gameObjects
     *          the list of all game objects of the current scene
     */
    void init(Scene scene, List<GameObject> gameObjects);

    /**
     * @return the current {@link Scene} to be shown
     */
    Scene getScene();


    /**
     * @return the list of {@link GameObject} present in the current {@link Scene}
     */
    List<GameObject> getGameObjects();

    /**
     * Draw the {@link GameObject}s of the current environment on the {@link Scene}.
     */
    void render();
}
