package puttingchallenge.view.controllers;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import puttingchallenge.common.Point2D;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.GameEventWithDetailsImpl;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} related to the loaded game level.
 * 
 */
public class LevelController extends AbstractSceneController implements EventHandler<Event> {

    private static final double UPPER_LEFT_X = 0.0;
    private static final double UPPER_LEFT_Y = 0.0;

    private GraphicsContext gc;
    private boolean isAiming;
    private Optional<Point2D> aimingPoint;
    private String pathBackground;

    /**
     * Initialize a new {@link LevelController}.
     * 
     * @param scene
     *            {@link Scene} relating to the loaded game level.
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param gc
     *            the {@link GraphicsContext} in which the object has to be drawn
     * @param pathBackground
     *            the path of the background
     */
    public void init(final Scene scene,
                     final List<GameObject> gameObjects,
                     final GraphicsContext gc,
                     final String pathBackground) throws FileNotFoundException {
        super.init(scene, gameObjects);
        this.gc = gc;
        this.isAiming = false;
        this.pathBackground = pathBackground;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
        final Image background = new Image(getClass().getResource(pathBackground).toExternalForm());
        gc.clearRect(UPPER_LEFT_X, UPPER_LEFT_Y, super.getScene().getWidth(), getScene().getHeight());
        gc.drawImage(background, UPPER_LEFT_X, UPPER_LEFT_Y);
        super.getGameObjects().stream().forEach(e -> e.draw(gc));
    }

    /**
     * Handles mouse moved event.
     * @param mouseMoved
     */
    public void handleMouseMoved(final MouseEvent mouseMoved) {
//        if (this.isAiming) {
//            final Pair<Double, Double> coord = this.getCoord(event);
//            final GameEventWithDetailsImpl<Pair<Double, Double>> movingEvent = new GameEventWithDetailsImpl<>(GameEventType.MOVING, coord);
//            this.getMediator().notifyColleagues(movingEvent, this);
//        }
    }

    /**
     * Handles mouse pressed event.
     * @param event
     */
    public void handleMousePressed(final MouseEvent event) {
        this.aimingPoint = Optional.of(this.getCoord(event));
    }

    /**
     * Handles mouse released event.
     * @param event
     */
    public void handleMouseReleased(final MouseEvent event) {
        if (this.aimingPoint.isPresent()) {
            final Pair<Point2D, Point2D> points = new Pair<>(this.aimingPoint.get(), this.getCoord(event));
            final GameEventWithDetailsImpl<Pair<Point2D, Point2D>> shootingEvent = new GameEventWithDetailsImpl<>(GameEventType.SHOOT, points);
            this.getMediator().notifyColleagues(shootingEvent, this);
        }
    }

    /**
     * Method that handle the action on the quit button.
     * @param event
     */
    public void handleQuit(final ActionEvent event) {
        final GameEventImpl quitEvent = new GameEventImpl(GameEventType.SHOW_MAIN_MENU);
        this.getMediator().notifyColleagues(quitEvent, this);
    }

    private Point2D getCoord(final MouseEvent event) {
        return new Point2D(event.getScreenX(), event.getScreenY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Event event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            this.handleQuit((ActionEvent) event);
            event.consume();
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            this.handleMouseReleased((MouseEvent) event);
            event.consume();
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            this.handleMousePressed((MouseEvent) event);
            event.consume();
        }
    }
}
