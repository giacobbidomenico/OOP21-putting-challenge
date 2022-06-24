package puttingchallenge.view.graphics;

import java.io.File;

/**
 * Class that implements the graphical representation of a wall in the game.
 * 
 */
public class WallGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
<<<<<<< HEAD
    private static final String TEXTURE_PATH_IMAGE = SEP + "obstacles" + SEP + "hole.png";
=======
    private static final String TEXTURE_PATH_IMAGE = SEP + "resources"
                                                     + SEP + "obstacles"
                                                     + SEP + "hole.png";
>>>>>>> 2f8b3b7bcb87180a838762cd99894fd6639b4e98

    /**
     * Build a new {@link GraphicComponent} for a wall in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the wall is contained
     * @param h
     *         the height of the {@link Image} where the wall is contained
     */
    public WallGraphicComponent(final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, w, h);
    }

}
