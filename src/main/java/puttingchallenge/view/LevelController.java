package puttingchallenge.view;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.model.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} related to the loaded game level.
 * 
 */
public class LevelController extends AbstractSceneController {

    private final GraphicsContext gc;

    /**
     * Build a new {@link LevelController}.
     * 
     * @param scene
     *            {@link Scene} relating to the loaded game level.
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param view
     *            {@link View} where the {@link Scene} is displayed
     * @param gc
     *            the {@link GraphicsContext} in which the object has to be drawn
     *
     */
    public LevelController(final Scene scene,
                           final List<GameObject> gameObjects,
                           final View view,
                           final GraphicsContext gc) {
        super(scene, gameObjects, view);
        this.gc = gc;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
       super.getGameObjects().stream().peek(e -> e.draw(gc));
    }

}
