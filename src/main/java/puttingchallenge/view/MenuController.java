package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.gameobjects.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} related to the main menu.
 * 
 */
public class MenuController extends AbstractSceneController {

    /**
     * Build a new {@link MenuController}.
     * 
     * @param scene
     *            {@link Scene} relating to the main menu
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param view
     *            {@link View} where the {@link Scene} is displayed
     */
    public MenuController(final Scene scene, 
                          final List<GameObject> gameObjects, 
                          final View view) {
        super(scene, gameObjects, view);
    }

}
