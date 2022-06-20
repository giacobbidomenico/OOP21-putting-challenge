package puttingchallenge.graphics;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.model.GameObject;

/**
 * Class that implements the graphical representation of the player.
 */
public class PlayerGraphicComponent extends AbstractGraphicComponent {
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;

    private boolean show;
    /**
     * Create a new {@link GraphicComponent} for the player.
     * @param imagePath
     *          of the skin
     */
    public PlayerGraphicComponent(final String imagePath) {
        super(imagePath, WIDTH, HEIGHT);
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
            super.draw(obj, gc);
        }
    }
}
