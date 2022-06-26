package puttingchallenge.view.graphics;

import java.io.File;

/**
<<<<<<< HEAD
 * Class that implements the graphical representation of the land in the game.
=======
 * Class that implements the graphical representation of a wall in the game.
>>>>>>> master
 * 
 */
public class LandGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
    private static final String TEXTURE_PATH_IMAGE = SEP + "obstacles" + SEP + "land.jpg";

    /**
<<<<<<< HEAD
     * Build a new {@link GraphicComponent} for a land in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the land is contained
     * @param h
     *         the height of the {@link Image} where the land is contained
=======
     * Build a new {@link GraphicComponent} for a wall in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the wall is contained
     * @param h
     *         the height of the {@link Image} where the wall is contained
>>>>>>> master
     */
    public LandGraphicComponent(final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, w, h);
    }

}
