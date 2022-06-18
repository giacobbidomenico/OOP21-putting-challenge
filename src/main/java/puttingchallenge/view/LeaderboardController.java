package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import puttingchallenge.model.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} related to the leader board.
 * 
 */
public class LeaderboardController extends AbstractSceneController {

    /**
     * Build a new {@link LeaderboardController}.
     * 
     * @param scene
     *            {@link Scene} relating to the leader board
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param view
     *            {@link View} where the {@link Scene} is displayed
     *
     */
    public LeaderboardController(final Scene scene, 
                                 final List<GameObject> gameObjects, 
                                 final View view) {
        super(scene, gameObjects, view);
    }
}
