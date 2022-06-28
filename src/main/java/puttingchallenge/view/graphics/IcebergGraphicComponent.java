package puttingchallenge.view.graphics;

import puttingchallenge.common.FileManager;

/**
 * Class that implements the graphical representation of an iceberg in the game.
 * 
 */
public class IcebergGraphicComponent extends AbstractGraphicComponent {

    private static final String TEXTURE_PATH_IMAGE = FileManager.OBSTACLES_SKINS_PATH + "iceberg.png";

    /**
     * Build a new {@link GraphicComponent} for an iceberg in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the iceberg is contained
     * @param h
     *         the height of the {@link Image} where the iceberg is contained
     */
    public IcebergGraphicComponent(final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, w, h);
    }

}
