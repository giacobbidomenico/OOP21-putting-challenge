package puttingchallenge.view.controllers;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.gameobjects.GameObject;

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
     * @param mediator
     *          the mediator between view, controller and model
     */
    void init(Scene scene, List<GameObject> gameObjects, Mediator mediator);

    /**
     * @return the current {@link Scene} to be shown
     * 
     */
    Scene getScene();


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
