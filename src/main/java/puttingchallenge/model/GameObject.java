package puttingchallenge.model;

import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.physics.PhysicsComponent;
import puttingchallenge.view.SceneType;

/**
 * Class that implements an element of the game.
 */
public interface GameObject {

    /**
     * Types of the game objects.
     */
    enum GameObjectType { 
        
        BALL(1),
        
        STATIC_OBSTACLE(2),
        
        PLAYER(3),
        
        WALL(4),
        
        TREE(5);
        
        private final int index;

        GameObjectType(final int index) {
            this.index = index;
        }

        /**
         * @param index
         *              the index of the game object type
         * @return a {@link GameObjectType} indexed by the {@code index}
         */
        public Optional<GameObjectType> getIndex(final int index) {
            for (GameObjectType t : GameObjectType.values()) {
                if (t.index == index) {
                    return Optional.of(t);
                }
            }
            return Optional.empty();
        }

    }

    /**
     * Sets the coordinates corresponding to the position of the object.
     * @param position
     *          position to assign
     */
    void setPosition(Point2D position);

    /**
     * Sets the velocity of the object.
     * @param vel
     *          velocity vector to assign
     */
    void setVelocity(Vector2D vel);

    /**
     * @return the type of the object.
     */
    GameObjectType getType();

    /**
     * @return the coordinates of the object.
     */
    Point2D getPosition();

    /**
     * @return the velocity of the object.
     */
    Vector2D getVelocity();

    /**
     * Update physic state of the object.
     * 
     * @param dt
     *          instant of time.
     * @param env
     *          environment of the game.
     */
    void updatePhysics(long dt, Environment env);

    /**
     * @return the {@link PhysicsComponent} of the object.
     */
    PhysicsComponent getPhysicsComponent();

    /**
     * Draw the game object skin in the actual scene.
     *
     * @param graphicsContext
     *          the {@link GraphicsContext} of the {@link Canvas} 
     *          where the skin of the {@link GameObject} will be drawn
     */
    void draw(GraphicsContext graphicsContext);

}
