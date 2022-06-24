package puttingchallenge.view.graphics;

import java.io.File;

/**
 * Represent the graphic behavior of the game ball.
 */
public final class BallGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
<<<<<<< HEAD
    private static final String SKIN_PATH = SEP + "skins" + SEP + "ball.png";
=======
    private static final String SKIN_PATH = SEP + "resources"
                                            + SEP + "skins"
                                            + SEP + "ball.png";
>>>>>>> 2f8b3b7bcb87180a838762cd99894fd6639b4e98

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
