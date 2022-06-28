package puttingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameFactory;
import puttingchallenge.model.Environment;
import puttingchallenge.model.EnvironmentImpl;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * 
 */
class TestEnvironment {

    private static final double WIDTH_CONTAINER = 390;
    private static final double HIGHT_CONTAINER = 200;
    private final GameObject ball;
    private final GameObject player;
    private final GameObject land;
    private final GameObject tree;
    private final GameObject wall;
    private final GameObject iceberg;
    private final GameObject hole;
    private final GameFactory factory = new GameFactory();

    /**
     * 
     */
    TestEnvironment() {
        this.ball = factory.createBall(new Point2D(15, 3), 12);
        this.player = factory.createPlayer(new Point2D(12, 4), "player.jpg", 12, 13);
        this.land = factory.createLand(new Point2D(0, 0), 100, 10);
        this.tree = factory.createTree(new Point2D(30, 60), 15, 30);
        this.wall = factory.createWall(new Point2D(50, 60), 30, 30);
        this.iceberg = factory.createIceberg(new Point2D(90, 60), 30, 30);
        this.hole = factory.createHole(new Point2D(130, 60), 49, 30);
    }

    private Environment insertElementsInTheEnvironment() {
        final List<GameObject> staticObstacles = List.of(land, tree, wall, iceberg);
        return new EnvironmentImpl(new Rectangle2D(0, 0, WIDTH_CONTAINER, HIGHT_CONTAINER),
                                   ball, 
                                   player, 
                                   staticObstacles, 
                                   hole);
    }

    /**
     * Correct.
     */
    @Test void testCorrectEnvironment() {
        final Environment env = this.insertElementsInTheEnvironment();
        assertEquals(true, true);
        assertEquals(env.getBall(), this.ball);
    }
}
