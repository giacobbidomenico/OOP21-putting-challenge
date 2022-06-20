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
    public GameStatus getStatus() {
        return this.status;
    }
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
        this.setStatus(GameStatus.AIMING);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot(final Point2D releasedOnPoint) {
        this.getEnvironment().getBall().setVelocity(Vector2D.getVectorFrom(releasedOnPoint, this.startingPoint));
        this.setStatus(GameStatus.SHOOTING);
    }
    /**
     * Moves the player next to the ball.
     */
    private void movePlayer() {
        this.currentEnvironment.movePlayer();
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
            this.setStatus(GameStatus.GAME_OVER);
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
    /**
     * Update the status of the game.
     * @param status
     */
    private void setStatus(final GameStatus status) {
        this.status = status;
    }
}
