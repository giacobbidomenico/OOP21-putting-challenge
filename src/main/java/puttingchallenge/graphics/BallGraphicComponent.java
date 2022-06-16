package puttingchallenge.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represent the graphic behavior of the game ball.
 */
public final class BallGraphicComponent extends AbstractGraphicComponent {

    private static final String SKIN_PATH = "";

    /**
     * Build a new {@link GraphicComponent} for the game ball.
     * 
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     */
    public BallGraphicComponent(final GraphicsContext gc) {
        super(SKIN_PATH, gc);
    }

}
