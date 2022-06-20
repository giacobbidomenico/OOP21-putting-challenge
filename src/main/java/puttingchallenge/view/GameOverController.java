package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.gameobjects.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} shown at the game over.
 * 
1*/
public class GameOverController extends AbstractSceneController {

    /**
     * Build a new {@link GameOverController}.
     * 
     * @param scene
     *            {@link Scene} shown at the game over.
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param view
     *            {@link View} where the {@link Scene} is displayed
     *
     */
    public GameOverController(final Scene scene,
                              final List<GameObject> gameObjects,
                              final View view) {
        super(scene, gameObjects, view);
    }

}
