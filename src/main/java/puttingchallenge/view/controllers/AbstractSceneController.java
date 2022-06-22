package puttingchallenge.view.controllers;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Abstract class that defines the controller that manages the {@link Scene} of the {@link View}.
 * 
 */
public abstract class AbstractSceneController implements SceneController {

    private Scene scene;
    private List<GameObject> gameObjects;
    private Mediator mediator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Scene scene, final List<GameObject> gameObjects) { 
        this.scene = scene;
        this.gameObjects = gameObjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return scene;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() { }

    /**
     * @return the mediator between view, controller and model
     */
    protected Mediator getMediator() {
        return this.mediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
       this.mediator = mediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent event) { }

}
