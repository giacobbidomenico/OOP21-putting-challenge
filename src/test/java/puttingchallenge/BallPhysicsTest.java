package puttingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.core.GameFactory;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.physics.BallPhysicsComponent;

/**
 *
 */
class BallPhysicsTest {

    private static final double BALL_X = 30;
    private static final double BALL_Y = 40;
    private static final double RADIUS = 5;
    private static final double NUM1 = 5;
    private final GameFactory factory = new GameFactory();
    private GameObject ball;

    @BeforeEach
    void setUp() {
        final JFrame frame = new JFrame();
        final JFXPanel jFx = new JFXPanel();
        frame.add(jFx);
        this.ball = factory.createBall(new Point2D(BALL_X, BALL_Y), RADIUS);
    }

    /**
     * Given a zero velocity check if the ball is still moving.
     */
    @Test void testBallStopped() {
        final var bf = (BallPhysicsComponent) this.ball.getPhysicsComponent();
        bf.setVelocity(new Vector2D(0, 0));
        assertFalse(bf.isMoving());
    }

    /**
     * Given a not-zero velocity check if the ball is moving.
     */
    @Test void testBallMoving() {
        final var bf = (BallPhysicsComponent) this.ball.getPhysicsComponent();
        bf.setVelocity(new Vector2D(NUM1, NUM1));
        assertTrue(bf.isMoving());
        assertEquals(bf.getVelocity(), new Vector2D(NUM1, NUM1));
    }

    /**
     * Given a velocity and a delta time checks the correct position where the ball should be.
     */
    @Test void testNextPosBall() {
        final var bf = (BallPhysicsComponent) this.ball.getPhysicsComponent();
        final var copyBall = this.factory.createBall(this.ball.getPosition(), bf.getRadius());
        final var bfCopy = (BallPhysicsComponent) copyBall.getPhysicsComponent();
        var newPos = bf.nextPos(Double.valueOf(NUM1).longValue(), copyBall.getPosition());
        assertEquals(new Point2D(30.0, 40.0082771875), newPos);
        bfCopy.setVelocity(new Vector2D(10, 10));
        newPos = bfCopy.nextPos(200, copyBall.getPosition());
        assertEquals(new Point2D(33.0, 56.243500000000004), newPos);
    }

}
