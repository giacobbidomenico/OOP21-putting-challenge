package puttingchallenge.view.graphics;

import puttingchallenge.common.FileManager;

/**
 * Class that implements the graphical representation of a shelf in the game.
 * 
 */
public class ShelfGraphicComponent extends AbstractGraphicComponent {

    private static final String TEXTURE_PATH_IMAGE = FileManager.OBSTACLES_SKINS_PATH + "shelf.png";

    /**
     * Build a new {@link GraphicComponent} for a shelf in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the shelf is contained
     * @param h
     *         the height of the {@link Image} where the shelf is contained
     */
    public ShelfGraphicComponent(final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, w, h);
    }

}
