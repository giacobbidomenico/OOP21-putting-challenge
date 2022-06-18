package puttingchallenge.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class that implements the graphical representation of a tree in the game.
 * 
 */
public class TreeGraphicComponent extends AbstractGraphicComponent {

    private static final String IMAGE_PATH = "";

    /**
     * Build a new {@link GraphicComponent} for a tree in the game.
     * 
     * @param gc
     *         the {@link GraphicsContext} in which the object has to be drawn
     * @param w
     *         the width of the image where the tree is contained
     * @param h
     *         the height of the image where the tree is contained
     */
    public TreeGraphicComponent(final GraphicsContext gc, final double w, final double h) {
        super(IMAGE_PATH, gc, w, h);
    }
}
