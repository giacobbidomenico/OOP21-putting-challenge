package puttingchallenge.view.graphics;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Interface that represent the graphical component of a {@link GameObject}.
 */
public interface GraphicComponent {

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
