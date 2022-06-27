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
    /**
     * Create a new {@link GameObjectImpl} representing the player.
     * @param type
     * @param position
     * @param graph
     * @param phys
     * @param concreteDynamicBoundingBox
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
     * @return .
     */
    public ConcreteDynamicBoundingBox getHitBox() {
        return this.hitbox;
    }

    /**
     * @return .
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return .
     */
    public double getHeight() {
        return this.height;
    }

}
