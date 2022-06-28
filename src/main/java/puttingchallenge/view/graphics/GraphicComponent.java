package puttingchallenge.view.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Interface that represent the graphical component of a {@link GameObject}.
 */
public interface GraphicComponent {

    /**
     * @return the {@link Image} of the {@link GameObject} skin.
     */
    Image getSkin();

    /**
     * @return the width of the {@link GameObject}.
     */
    double getWidth();

    /**
     * @return the width of the {@link GameObject}.
     */
    double getHeight();

    /**
     * Draw the game object skin in the actual scene.
     * 
     * @param obj
     *          the instance of {@link GameObject} to be drawn
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     */
    void draw(GameObject obj, GraphicsContext gc);

}
