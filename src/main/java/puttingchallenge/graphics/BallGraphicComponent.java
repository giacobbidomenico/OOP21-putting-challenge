package puttingchallenge.graphics;

/**
 * Represent the graphic behavior of the game ball.
 */
public final class BallGraphicComponent extends AbstractGraphicComponent {

    private static final String SKIN_PATH = "";

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
