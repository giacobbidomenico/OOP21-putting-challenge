package puttingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import puttingchallenge.model.gameobjects.BallObjectImpl;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;
import puttingchallenge.model.gameobjects.PlayerObject;
import puttingchallenge.model.physics.BallPhysicsComponent;

/**
 *
 */
class EnvironmentTest {
    private static final double NUM1 = 20;
    private static final double NUM2 = 30;
    private static final double NUM3 = 40;
    private static final double NUM4 = 50;
    private static final double NUM5 = 100;
    private static final double NUM6 = 80;
    private static final Point2D MAIN_POSITION = new Point2D(NUM1, NUM2);
    private static final Point2D ANOTHER_POSITION = new Point2D(NUM3, NUM4);

    private Rectangle2D container;
    private BallObjectImpl ball;
    private PlayerObject player;
    private GameObject land;
    private GameObject wall;
    private GameObject tree;
    private GameObject iceberg;
    private GameObject hole;
    private List<GameObject>  staticObstacles;
    private final GameFactory factory = new GameFactory();

    /**
     * 
     * @return .
     */
    private Environment initEnvironment() {
        return new EnvironmentImpl(container, 
                                   (BallObjectImpl) ball,
                                   player, 
                                   staticObstacles, 
                                   hole);
    }

    @BeforeEach
    void setUp() {
        final JFrame frame = new JFrame();
        final JFXPanel jFx = new JFXPanel();
        frame.add(jFx);
        this.container = new Rectangle2D(0, 0, NUM1, NUM2);
        this.ball = (BallObjectImpl) factory.createBall(ANOTHER_POSITION, NUM1);
        this.player = factory.createPlayer(MAIN_POSITION, "/skins/player.png", NUM1, NUM2, false);
        this.land = factory.createLand(MAIN_POSITION, NUM1, NUM2);
        this.wall = factory.createWall(MAIN_POSITION, NUM1, NUM2);
        this.tree = factory.createTree(MAIN_POSITION, NUM1, NUM2);
        this.iceberg = factory.createFootball(MAIN_POSITION, NUM1, NUM2);
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

        assertEquals(env.getBall().getPosition(), ANOTHER_POSITION);
        assertEquals(env.getPlayer().getPosition(), MAIN_POSITION);
        assertEquals(env.getHole().getPosition(), MAIN_POSITION);

        final List<Point2D> obstaclesPosition =  env.getStaticObstacles().stream()
                .map(e -> e.getPosition())
                .collect(Collectors.toList());
        assertEquals(obstaclesPosition, List.of(MAIN_POSITION,
                                                MAIN_POSITION,
                                                MAIN_POSITION,
                                                MAIN_POSITION));

        assertNotEquals(obstaclesPosition, List.of(ANOTHER_POSITION,
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
        final Environment env =  buildEnv.addBall(ANOTHER_POSITION, NUM1)
                                         .addContainer(container)
                                         .addPlayer(MAIN_POSITION, "/skins/player.png", NUM1, NUM2, false)
                                         .addStaticObstacle(GameObjectType.LAND, MAIN_POSITION, NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.WALL, MAIN_POSITION, NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.TREE, MAIN_POSITION, NUM1, NUM2)
                                         .addStaticObstacle(GameObjectType.FOOTBALL, MAIN_POSITION, NUM1, NUM2)
                                         .addHole(MAIN_POSITION, NUM1, NUM2)
                                         .build();
        assertEquals(this.initEnvironment(), env);
    }

    /**
     * 
     */
    @Test void checkCollisions() {
        final var env = this.initEnvironment();
        assertFalse(env.checkCollisions(ball.getHitBox(),
                                       (BallPhysicsComponent) ball.getPhysicsComponent(), 
                                       ball.getPosition(),
                                       Double.valueOf(NUM1).longValue()).isPresent());

        final GameObject landCopy = factory.createHole(new Point2D(NUM5, NUM6),
                this.land.getGraphicComponent().getWidth(), 
                this.land.getGraphicComponent().getHeight());
        assertFalse(env.checkCollisions(ball.getHitBox(), 
                                        (BallPhysicsComponent) ball.getPhysicsComponent(), 
                                        landCopy.getPosition(),
                                        Double.valueOf(NUM1).longValue()).isPresent());
    }

    /**
     * 
     */
    @Test void checkCollisionWithHole() {
        final var env = this.initEnvironment();

        assertTrue(env.checkCollisions(ball.getHitBox(), 
                                      (BallPhysicsComponent) ball.getPhysicsComponent(), 
                                       this.hole.getPosition(),
                                       Double.valueOf(NUM2).longValue()).isPresent());

        final GameObject holeCopy = factory.createHole(new Point2D(NUM5, NUM6),
                this.hole.getGraphicComponent().getWidth(), 
                this.hole.getGraphicComponent().getHeight());
        assertFalse(env.checkCollisions(ball.getHitBox(), 
                                        (BallPhysicsComponent) ball.getPhysicsComponent(), 
                                        holeCopy.getPosition(),
                                        Double.valueOf(NUM2).longValue()).isPresent());
    }
}
