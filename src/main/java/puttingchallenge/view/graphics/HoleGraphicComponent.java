package puttingchallenge.view.graphics;

import java.io.File;

/**
 * Class that implements the graphical representation of the game hole.
 * 
 */
public class HoleGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
    private static final String IMAGE_OF_THE_HOLE = System.getProperty("user.dir")
                                                    + SEP + "src"
                                                    + SEP + "main"
                                                    + SEP + "resources"
                                                    + SEP + "obstacles"
                                                    + SEP + "hole.png";

    /**
     * Build a new {@link HoleGraphicComponent}.
     * 
     * @param w
     *        the width of the {@link Image} where the hole is contained
     * @param h
     *        the height of the {@link Image} where the hole is contained
     */
    public HoleGraphicComponent(final double w, 
                                final double h) {
        super(IMAGE_OF_THE_HOLE, w, h);
    }

}
