package puttingchallenge.model.gameobjects;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.ConcreteDynamicBoundingBox;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;
/**
 * Class that represent the player.
 */
public class PlayerObject extends AbstractGameObject {

    private Bat bat;
    private final ConcreteDynamicBoundingBox hitbox;
    private final double width;
    private final double height;
    private boolean flip;

    /**
     * Create a new {@link GameObject} representing the player.
     * @param type
     * @param position
     * @param graph
     *          the {@link GraphicComponent} of the object
     * @param phys
     *          the {@link PhysicsComponent} of the object
     * @param concreteDynamicBoundingBox
     *          the hit-box of the object
     * @param width
     * @param height
     */
    public PlayerObject(final GameObjectType type, 
                        final Point2D position, 
                        final GraphicComponent graph, 
                        final PhysicsComponent phys,
                        final ConcreteDynamicBoundingBox concreteDynamicBoundingBox,
                        final double width,
                        final double height) {
        super(type, position, graph, phys);
        this.bat = new Bat(BatType.HYBRID);
        this.hitbox = concreteDynamicBoundingBox;
        this.width = width;
        this.height = height;
    }

    /**
     * Set a new bat for the player.
     * @param bat
     *          to use
     */
    public void setBat(final Bat bat) {
        this.bat = bat;
    }

    /**
     * @return
     *      the bat in use
     */
    public Bat getBat() {
        return this.bat;
    }

    /**
     * @return 
     *          the hit-box of the player
     */
    public ConcreteDynamicBoundingBox getHitBox() {
        return this.hitbox;
    }

    /**
     * @return
     *          the width of the player
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return
     *          the height of the player
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the flip of the {@link GameObject}.
     * 
     * @param flip
     *          flip of the {@link GameObject}
     */
    public void setFlip(final boolean flip) {
        this.flip = flip;
    }

    /**
     * @return the flip of the {@link GameObject}
     */
    public boolean isFlip() {
        return this.flip;
    }

}
