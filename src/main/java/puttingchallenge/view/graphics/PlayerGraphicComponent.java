package puttingchallenge.view.graphics;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.gameobjects.GameObject;

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
        if (obj.isFlipped()) {
            obj.setPosition(new Point2D(-obj.getPosition().getX(), obj.getPosition().getY()));
        }
        if (this.show) {
            super.draw(obj, gc);
        }
    }
}
