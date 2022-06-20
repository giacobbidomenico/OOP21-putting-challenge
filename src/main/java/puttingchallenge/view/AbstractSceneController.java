package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.gameobjects.GameObject;

/**
 * Abstract class that defines the controller that manages the {@link Scene} of the {@link View}.
 * 
 */
public abstract class AbstractSceneController implements SceneController {

    private final Scene scene;
    private final List<GameObject> gameObjects;
    private final View view;

    /**
     * Build a new {@link AbstractSceneController}.
     * 
     * @param scene
     *            {@link Scene} managed by the controller
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param view
     *            {@link View} where the {@link Scene} is displayed
     *
     */
    public AbstractSceneController(final Scene scene, 
                                   final List<GameObject> gameObjects, 
                                   final View view) { 
        this.scene = scene;
        this.gameObjects = gameObjects;
        this.view = view;
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
    public View getView() {
        return view;
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
