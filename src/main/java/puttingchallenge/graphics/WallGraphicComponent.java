package puttingchallenge.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class that implements the graphical representation of a wall in the game.
 * 
 */
public class WallGraphicComponent extends AbstractGraphicComponent {

    private static final String TEXTURE_PATH_IMAGE = "";

    /**
     * Build a new {@link GraphicComponent} for a wall in the game.
     * 
     * @param gc
     *         the {@link GraphicsContext} in which the object has to be drawn
     * @param w
     *         the width of the image where the wall is contained
     * @param h
     *         the height of the image where the wall is contained
     */
    public WallGraphicComponent(final GraphicsContext gc, final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, gc, w, h);
    }

}
