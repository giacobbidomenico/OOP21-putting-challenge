package puttingchallenge.view.graphics;

import java.io.File;

/**
 * Class that implements the graphical representation of the land in the game.
 * 
 */
public class LandGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
    private static final String TEXTURE_PATH_IMAGE = SEP + "obstacles" + SEP + "land.jpg";

    /**
     * Build a new {@link GraphicComponent} for a wall in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the wall is contained
     * @param h
     *         the height of the {@link Image} where the wall is contained
     */
    public LandGraphicComponent(final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, w, h);
    }

}
