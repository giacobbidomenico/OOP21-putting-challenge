package puttingchallenge.view.graphics;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Interface that represent the graphical component of a {@link GameObject}.
 */
public interface GraphicComponent {

    /**
     * @return the path of the {@link Image} corresponding to the {@link GameObject} skin.
     */
    String getPathSkin();

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
