package puttingchallenge.model;
/**
 * 
 * Interface for maintaining and handling the state of the game.
 *
 */
public interface GameState {
    /**
     *
     */
    int NO_LIVES = 0;
    /**
     * @return
     *      the current environment
     */
    Environment getEnvironment();
    
}
