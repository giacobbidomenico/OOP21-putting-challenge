package puttingchallenge.model.events;

/**
 * Enumeration for the different event types notified 
 * from the {@link GameState} to the {@link Environment} and vice-versa.
 */
public enum ModelEventType {
    /**
     * The ball stopped inside the map.
     */
    BALL_STOPPED,
    /**
     * The ball stopped in the hole.
     */
    BALL_IN_HOLE,
    /**
     * The ball is outside the map. 
     */
    BALL_OUT_OF_BOUND,
    /**
     * The player shot the ball.
     */
    SHOOT,
    /** 
     * The player can shoot.
     */
    WAITING,
    /**
     * The player has to move next to the ball in order to re-try.
     */
    MOVE_PLAYER;
}
