package puttingchallenge.view.graphics;

/**
 * Class that implements the graphical representation of a tree in the game.
 * 
 */
public class TreeGraphicComponent extends AbstractGraphicComponent {

    private static final String IMAGE_PATH = "";

    /**
     * Build a new {@link GraphicComponent} for a tree in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the tree is contained
     * @param h
     *         the height of the {@link Image} where the tree is contained
     */
    public TreeGraphicComponent(final double w, final double h) {
        super(IMAGE_PATH, w, h);
    }
}
