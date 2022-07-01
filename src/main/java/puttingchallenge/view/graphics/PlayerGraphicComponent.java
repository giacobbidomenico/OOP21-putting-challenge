package puttingchallenge.view.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.PlayerObject;

/**
 * Class that implements the graphical representation of the player.
 */
public class PlayerGraphicComponent extends AbstractGraphicComponent {

    private boolean show;

    /**
     * Create a new {@link GraphicComponent} for the player.
     * @param imagePath
     *          of the skin
     * @param width
     * @param height
     */
    public PlayerGraphicComponent(final String imagePath, final double width, final double height) {
        super(imagePath, width, height);
        this.show = true;
    }

    /**
     * Change the player visibility in the game.
     */
    public void changeShowStatus() {
        this.show = !this.show;
    }

    /**
     * Render the player only if visible.
     */
    @Override
    public void draw(final GameObject obj, final GraphicsContext gc) {
        if (this.show) {
            if (((PlayerObject) obj).isFlip()) {
                final Point2D pos = obj.getPosition();
                final double w = super.getWidth();
                final double h = super.getHeight();
                final Image skin = super.getSkin();
                gc.drawImage(skin, pos.getX() + w, pos.getY(), -w, h);
            } else {
                super.draw(obj, gc);
            }
        }
    }

}
