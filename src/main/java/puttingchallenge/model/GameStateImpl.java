package puttingchallenge.model;

import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;

/**
 * Class that implements the game state.
 */
public class GameStateImpl implements GameState {
    private int score;
    private int lives;
    private Environment currentEnvironment;
    private GameStatus status;
    private Point2D startingPoint;
    /**
     * {@inheritDoc}
     */
    @Override
    public Environment getEnvironment() {
        return this.currentEnvironment;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void aim(final Point2D pressedOnPoint) {
        this.startingPoint = pressedOnPoint;
        this.status = GameStatus.AIMING;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot(final Point2D releasedOnPoint) {
        this.getEnvironment().getBall().setVelocity(Vector2D.getVectorFrom(releasedOnPoint, this.startingPoint));
        this.status = GameStatus.SHOOTING;
    }
    /**
     * Check whether the game is over or not.
     * @return
     *      true if the game is over
     */
    private boolean isGameOver() {
        return this.lives == NO_LIVES;
    }
    /**
     * Moves the player next to the ball.
     */
    private void movePlayer() {
        this.currentEnvironment.movePlayer();
    }
    /**
     * Decrements the game score.
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
        if (this.lives == 0) {
            this.status = GameStatus.GAME_OVER;
            // game engine should be notified here
        }
    }
    /**
     * Increments lives due to in game boosts.
     */
    private void incLives() {
        this.lives++;
    }
    /**
     * Sets the appropriate environment for the specific state.
     */
    private void setNextEnvironment() {
        // TODO
    }
}
