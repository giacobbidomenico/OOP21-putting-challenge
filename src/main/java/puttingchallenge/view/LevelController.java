package puttingchallenge.view;

import javafx.scene.image.Image;
import puttingchallenge.gameobjects.GameObject;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class that defines the controller that manages the {@link Scene} related to the loaded game level.
 * 
 */
public class LevelController extends AbstractSceneController {

    private static final double UPPER_LEFT_X = 0.0;
    private static final double UPPER_LEFT_Y = 0.0;

    private final GraphicsContext gc;
    private final String background;

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
     * @param background
     */
    public LevelController(final Scene scene,
                           final List<GameObject> gameObjects,
                           final View view,
                           final GraphicsContext gc,
                           final String background) {
        super(scene, gameObjects, view);
        this.gc = gc;
        this.background = background;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
        final Image backgrounImage = new Image(background);
        this.gc.drawImage(backgrounImage, 
                          UPPER_LEFT_X, 
                          UPPER_LEFT_Y, 
                          super.getScene().getWidth(), 
                          super.getScene().getHeight());
        this.gc.clearRect(UPPER_LEFT_X,
                          UPPER_LEFT_Y, 
                          super.getScene().getWidth(), 
                          super.getScene().getHeight());
        super.getGameObjects().stream().peek(e -> e.draw(gc));
    }

}
