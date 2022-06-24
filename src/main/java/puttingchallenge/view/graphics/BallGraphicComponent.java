package puttingchallenge.view.graphics;

import java.io.File;

/**
 * Represent the graphic behavior of the game ball.
 */
public final class BallGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
    private static final String SKIN_PATH = SEP + "resources"
                                            + SEP + "skins"
                                            + SEP + "ball.png";

    /**
     * Build a new {@link GraphicComponent} for the game ball.
     * 
     * @param radius
     *          the radius of the ball
     */
    public BallGraphicComponent(final double radius) {
        super(SKIN_PATH, radius * 2, radius * 2);
    }

}
