package puttingchallenge.view.graphics;

import java.io.File;

/**
 * Class that implements the graphical representation of a football ball in the game.
 * 
 */
public class FootballGraphicComponent extends AbstractGraphicComponent {

    private static final String SEP = File.separator;
    private static final String TEXTURE_PATH_IMAGE = SEP + "obstacles" + SEP + "football.png";

    /**
     * Build a new {@link GraphicComponent} for a football ball in the game.
     * 
     * @param w
     *         the width of the {@link Image} where the football ball is contained
     * @param h
     *         the height of the {@link Image} where the football ball is contained
     */
    public FootballGraphicComponent(final double w, final double h) {
        super(TEXTURE_PATH_IMAGE, w, h);
    }

}
