package puttingchallenge.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import puttingchallenge.model.GameObject;

/**
 * Define the default behavior of a {@link GraphicComponent}.
 */
public class AbstractGraphicComponent implements GraphicComponent {

    private final Image skin;
    private final GraphicsContext gc;

    /**
     * Set up a new graphic component.
     * 
     * @param imagePath
     *          the path of the skin
     * @param gc
     *          the {@link GraphicsContext} in which the object has to be drawn
     */
    protected AbstractGraphicComponent(final String imagePath, final GraphicsContext gc) {
        this.skin = new Image(imagePath);
        this.gc = gc;
    }

    /**
     * {@inheritDoc}
     * 
     * The method can be overridden to change the graphic behavior of the object
     */
    @Override
    public void draw(final GameObject obj) {
        final double x = obj.getPosition().getX();
        final double y = obj.getPosition().getY();
        this.gc.drawImage(skin, x, y);
    }

}
