package puttingchallenge.view;

import javafx.scene.image.Image;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} related to the loaded game level.
 * 
 */
public class LevelController extends AbstractSceneController {

    private static final double UPPER_LEFT_X = 0.0;
    private static final double UPPER_LEFT_Y = 0.0;

    private GraphicsContext gc;
    private String background;

    /**
     * Initialize a new {@link LevelController}.
     * 
     * @param scene
     *            {@link Scene} relating to the loaded game level.
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param controller
     *            the controller of the application
     * @param gc
     *            the {@link GraphicsContext} in which the object has to be drawn
     * @param background
     */
    public void init(final Scene scene,
                     final List<GameObject> gameObjects,
                     final GameEngine controller,
                     final GraphicsContext gc,
                     final String background) {
        super.init(scene, gameObjects, controller);
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
