package puttingchallenge.view.graphics;

import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Define the default behavior of a {@link GraphicComponent}.
 */
public class AbstractGraphicComponent implements GraphicComponent {

    private final Image skin;
    private final String pathSkin;
    private final double w;
    private final double h;

    /**
     * Set up a new graphic component.
     * 
     * @param imagePath
     *          the path of the skin
     * @param w
     *          the width of the skin
     * @param h
     *          the height of the skin
     */
    protected AbstractGraphicComponent(final String imagePath,
                                       final double w,
                                       final double h) {
        System.out.println(imagePath);
        this.skin = new Image(imagePath);
        this.pathSkin = imagePath;
        this.w = w;
        this.h = h;
    }

    /**
     * {@inheritDoc}
     * 
     * The method can be overridden to change the graphic behavior of the object
     */
    @Override
    public void draw(final GameObject obj, final GraphicsContext gc) {
        gc.drawImage(skin, obj.getPosition().getX(), obj.getPosition().getY(), w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPathSkin() {
        return this.pathSkin;
    }

    /**
     * {@inheritDoc}
     */
    public double getWidth() {
        return this.w;
    }

    /**
     * {@inheritDoc}
     */
    public double getHeight() {
        return this.h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(h, skin, w);
    }

    /**
     * @return the {@link Image} of the component skin
     */
    protected Image getSkin() {
        return this.skin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof GraphicComponent) {
            final GraphicComponent graph = (GraphicComponent) obj;
            return pathSkin.equals(graph.getPathSkin())
                    && this.w == graph.getWidth()
                    && this.h == graph.getHeight();
        }
        return false;
    }

}
