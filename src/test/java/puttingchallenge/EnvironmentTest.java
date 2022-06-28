package puttingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import puttingchallenge.common.Point2D;
import puttingchallenge.common.Vector2D;
import puttingchallenge.core.GameFactory;
import puttingchallenge.model.BuilderEnvironment;
import puttingchallenge.model.BuilderEnvironmentImpl;
import puttingchallenge.model.Environment;
import puttingchallenge.model.EnvironmentImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;
import puttingchallenge.model.gameobjects.PlayerObject;

/**
 *
 */
class EnvironmentTest {
    private static final String SEP = File.separator;
    private static final double NUM1 = 23;
    private static final double NUM2 = 3;
    private static final double NUM3 = 10;
    private static final double NUM4 = 2;
    private static final Point2D MAIN_POSITION = new Point2D(NUM1, NUM2);
    private static final Point2D ANOTHER_POSITION = new Point2D(NUM3, NUM4);

    private Rectangle2D container;
    private GameObject ball;
    private PlayerObject player;
    private GameObject land;
    private GameObject wall;
    private GameObject tree;
    private GameObject iceberg;
    private GameObject hole;
    private List<GameObject>  staticObstacles;

    /**
     * 
     * @return .
     */
    private Environment initEnvironment() {
        return new EnvironmentImpl(container, 
                                   ball, 
                                   player, 
                                   staticObstacles, 
                                   hole);
    }

    @BeforeEach
    void setUp() {
        final JFrame frame = new JFrame();
        final JFXPanel jFx = new JFXPanel();
        frame.add(jFx);
        final GameFactory factory = new GameFactory();
        this.container = new Rectangle2D(0, 0, NUM1, NUM2);
        this.ball = factory.createBall(MAIN_POSITION, NUM1);
        this.player = factory.createPlayer(MAIN_POSITION, "/skins/player.png", NUM1, NUM2);
        this.land = factory.createLand(MAIN_POSITION, NUM1, NUM2);
        this.wall = factory.createWall(MAIN_POSITION, NUM1, NUM2);
        this.tree = factory.createTree(MAIN_POSITION, NUM1, NUM2);
        this.iceberg = factory.createIceberg(ANOTHER_POSITION, NUM1, NUM2);
        this.hole = factory.createHole(MAIN_POSITION, NUM1, NUM2);
        this.staticObstacles = List.of(this.land,
                                       this.wall,
                                       this.tree,
                                       this.iceberg);
    }

    /**
     * 
     */
    @Test void testEnvironment() {
        final Environment env = this.initEnvironment();
        assertEquals(env.getBall().getPosition(), MAIN_POSITION);
        assertEquals(env.getPlayer().getPosition(), MAIN_POSITION);
        assertEquals(env.getHole().getPosition(), MAIN_POSITION);
        final List<Point2D> obstaclesPosition =  env.getStaticObstacles().stream()
                .map(e -> e.getPosition())
                .collect(Collectors.toList());
        assertEquals(obstaclesPosition, List.of(MAIN_POSITION,
                                                MAIN_POSITION,
                                                MAIN_POSITION,
                                                ANOTHER_POSITION));
        assertNotEquals(obstaclesPosition, List.of(MAIN_POSITION,
                                                   MAIN_POSITION,
                                                   MAIN_POSITION,
                                                   MAIN_POSITION));
        assertEquals(this.land.getVelocity(), new Vector2D(0, 0));
        assertEquals(this.tree.getVelocity(), new Vector2D(0, 0));
        assertEquals(this.wall.getVelocity(), new Vector2D(0, 0));
        assertEquals(this.iceberg.getVelocity(), new Vector2D(0, 0));
    }

    /**
     * 
     */
    @Test void testBuilderEnvironment() {
        final BuilderEnvironment buildEnv = new BuilderEnvironmentImpl();
        final Environment env =  buildEnv.addBall(MAIN_POSITION, NUM1)
                                         .addContainer(container)
                                         .addPlayer(MAIN_POSITION, "/skins/player.png", NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.LAND, MAIN_POSITION, NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.WALL, MAIN_POSITION, NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.TREE, MAIN_POSITION, NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.ICEBERG, ANOTHER_POSITION, NUM1, NUM2)
                                         .addHole(MAIN_POSITION, NUM1, NUM2)
                                         .build();
        assertEquals(this.initEnvironment(), env);
    }
}
