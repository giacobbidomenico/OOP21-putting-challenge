package puttingchallenge.model;

import java.util.List;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.collisions.DynamicBoundingBox.CollisionTest;
import puttingchallenge.model.collisions.PassiveCircleBoundingBox;
import puttingchallenge.model.events.ModelEventType;
import puttingchallenge.model.events.ObservableEvents;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.PlayerObject;
import puttingchallenge.model.physics.BallPhysicsComponent;

/**
 * Interface that defines the game {@link Environment}.
 */
public interface Environment {
    /**
     * Update the game {@link Environment}.
     * 
     * @param dt
     *         instant of time.
     */
    void update(long dt);

    /**
     * Check if there has been a collision between several {@link GameObject}.
     * 
     * @param ballHitbox 
     *         {@link Hitbox} to collide with
     * @param ballPhysics 
     *         physics to move the {@link Hitbox}
     * @param ballPosition 
     *         previous position of the ball
     *
     * @return a info about the collision occurred, empty if no collision has occurred.
     */
    Optional<CollisionTest> checkCollisions(PassiveCircleBoundingBox ballHitbox, 
            BallPhysicsComponent ballPhysics, 
            Point2D ballPosition);

    /**
     * Adds a static obstacle to the game {@link Environment}.
     * 
     * @param obstacle
     *        static obstacle to add
     */
    void addStaticObstacle(GameObject obstacle);

    /**
     * @return the {@link GameObject} corresponding to the ball in the 
     *         game {@link Environment}
     */
    GameObject getBall();

    /**
     * @return the {@link GameObject} corresponding to the player in the 
     *         game {@link Environment}
     */
    PlayerObject getPlayer();

    /**
     * @return the {@link GameObject} corresponding to the hole in the 
     *         game {@link Environment}
     */
    GameObject getHole();

    /**
     * @return a list of {@link GameObject} corresponding to the static 
     *         obstacles in the game {@link Environment}
     */
    List<GameObject> getStaticObstacles();

    /**
     * Move the player next to the ball.
     */
    void movePlayer();

    /**
     * @return the {@link Rectangle2D} inside which there is the game {@link Environment}.
     */
    Rectangle2D getContainer();

    /**
     * Notifies if an event has been intercepted.
     */
    void notifyEvents();

    /**
     * Reads the events sent by the {@link GameState}.
     */
    void receiveEvents();

    /**
     * Configure the {@link ObservableEvents}, that allows the
     * communication from the {@link Environment} to the {@link GameState}.
     * 
     * @param observable
     *           the {@link ObservableEvents}, that allows the communication.
     */
    void configureObservable(ObservableEvents<ModelEventType> observable);

    /**
     * @return the {@link ObservableEvents}, that allows the
     * communication from the {@link GameState} to the {@link Environment}. 
     */
    ObservableEvents<ModelEventType> getObservable();

    /**
     * @return a {@link List} of {@link GameObject}s, where:
     *         -the first element is the player
     *         -the second element is the ball
     *         -the last element is the hole
     *         -the other elements are the obstacles
     */
    List<GameObject> getObjects();
}
