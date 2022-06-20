package puttingchallenge.graphics;
/**
 * Class that implements the graphical representation of the player.
 */
public class PlayerGraphicComponent extends AbstractGraphicComponent {
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    /**
     * Create a new {@link GraphicComponent} for the player.
     * @param imagePath
     *          of the skin
     */
    public PlayerGraphicComponent(final String imagePath) {
        super(imagePath, WIDTH, HEIGHT);
    }

}
