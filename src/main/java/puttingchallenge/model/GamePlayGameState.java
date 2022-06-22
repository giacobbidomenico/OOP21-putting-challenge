package puttingchallenge.model;
/**
 * Class that represent the in-game {@link GameState}.
 *
 */
public class GamePlayGameState extends AbstractGameState {
    private int score;
    private int lives;
    private static final int NO_LIVES = 0;
    private static final int NO_SCORE = 0;
    private static final int MAX_LIVES = 3;
    /**
     * 
     * @param manager
     * @param status
     */
    public GamePlayGameState(final GameStateManager manager, final GameStatus status) {
        super(manager, status);
        this.lives = MAX_LIVES;
        this.score = NO_SCORE;
    }
    /**
     * Decrements the game score.
     * Note that in game score could become negative in case the player takes penalties
     */
    private void decScore() {
        this.score--;
    }
    /**
     * Increments the game score.
     */
    private void incScore() {
        this.score++;
    }
    /**
     * Decrements lives.
     */
    private void decLives() {
        this.lives--;
        if (this.lives == NO_LIVES) {
            this.leavingState(GameStatus.GAME_OVER);
        }
    }
    /**
     * Increments lives due to in game boosts.
     */
    private void incLives() {
        this.lives++;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    void leavingState(final GameStatus nextStatus) {
        // write on file
        super.leavingState(nextStatus);
    }
}
