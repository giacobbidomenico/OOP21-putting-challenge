package puttingchallenge.model.gameobjects;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.DynamicBoundingBox;
import puttingchallenge.model.physics.PhysicsComponent;
import puttingchallenge.view.graphics.GraphicComponent;
/**
 * Class that represent the player.
 */
public class PlayerObject extends AbstractGameObject {
    private Bat bat;
    private final DynamicBoundingBox hitBox;
    /**
     * Create a new {@link GameObjectImpl} representing the player.
     * @param type
     * @param position
     * @param graph
     * @param phys
     * @param hitBox
     */
    public PlayerObject(final GameObjectType type, 
            final Point2D position, 
            final GraphicComponent graph, 
            final PhysicsComponent phys,
            final DynamicBoundingBox hitBox) {
        super(type, position, graph, phys);
        this.bat = new Bat(BatType.HYBRID);
        this.hitBox = hitBox;
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
     *          the hitbox of the object
     */
    public DynamicBoundingBox getHitBox() {
        return this.hitBox;
    }

}
