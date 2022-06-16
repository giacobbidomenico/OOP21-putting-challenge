package puttingchallenge.graphics;

import puttingchallenge.model.GameObject;

/**
 * Interface that represent the graphical component of a {@link GameObject}.
 */
public interface GraphicComponent {

    /**
     * Draw the game object skin in the actual scene.
     * 
     * @param obj
     *          the instance of {@link GameObject} to be drawn
     */
    void draw(GameObject obj);
}
