package puttingchallenge.model;
/**
 * 
 * Interface for maintaining and handling the states of the game.
 *
 */
public interface GameState {
    /**
     * @return
     *          the current status of the game
     */
    GameStatus getStatus();
}
