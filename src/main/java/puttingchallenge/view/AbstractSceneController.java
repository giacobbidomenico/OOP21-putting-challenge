package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject;

/**
 * Abstract class that defines the controller that manages the {@link Scene} of the {@link View}.
 * 
 */
public abstract class AbstractSceneController implements SceneController {

    private Scene scene;
    private List<GameObject> gameObjects;
    private GameEngine controller;

    /**
     * {@inheritDoc}
     */
    public void init(final Scene scene, 
                     final List<GameObject> gameObjects, 
                     final GameEngine controller) { 
        this.scene = scene;
        this.gameObjects = gameObjects;
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * {@inheritDoc}
     */
    public GameEngine getController() {
        return controller;
    }

    /**
     * {@inheritDoc}
     */
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
 
    }
}
