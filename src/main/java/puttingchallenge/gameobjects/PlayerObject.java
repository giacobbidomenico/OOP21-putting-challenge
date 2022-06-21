package puttingchallenge.gameobjects;

import puttingchallenge.common.Point2D;
import puttingchallenge.graphics.GraphicComponent;
import puttingchallenge.physics.PhysicsComponent;
/**
 * Class that represent the player.
 */
public class PlayerObject extends AbstractGameObject {
    private Bat bat;
    /**
     * Create a new {@link GameObjectImpl} representing the player.
     * @param position
     * @param graph
     * @param phys
     */
    public PlayerObject(final Point2D position, 
            final GraphicComponent graph, 
            final PhysicsComponent phys) {
        super(GameObjectType.PLAYER, position, graph, phys);
        this.bat = new Bat(BatType.HYBRID);
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

}
