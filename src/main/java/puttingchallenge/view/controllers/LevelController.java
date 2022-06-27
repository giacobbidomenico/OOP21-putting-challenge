package puttingchallenge.view.controllers;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
    private Optional<Point2D> aimingPoint;
    private Label score;
    private Label lives;

    /**
     * Initialize a new {@link LevelController}.
     * 
     * @param scene
     *            {@link Scene} relating to the loaded game level.
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param gc
     *            the {@link GraphicsContext} in which the object has to be drawn
     * @param score
     * @param lives
     */
    public void init(final Scene scene,
                     final List<GameObject> gameObjects,
                     final GraphicsContext gc,
                     final Label score,
                     final Label lives) throws FileNotFoundException {
        super.init(scene, gameObjects);
        this.gc = gc;
        this.score = score;
        this.lives = lives;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
        gc.clearRect(UPPER_LEFT_X, UPPER_LEFT_Y, super.getScene().getWidth(), getScene().getHeight());
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
        final Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("You are quitting the game!");
        alert.setContentText("You will be redirect to the main menu and the game will be lost. Are you sure?");
        final ButtonType okButton = new ButtonType("Ok");
        final ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(okButton, cancelButton);
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton) {
            final GameEventImpl quitEvent = new GameEventImpl(GameEventType.SHOW_MAIN_MENU);
            this.getMediator().notifyColleagues(quitEvent, this);
        }
    }

    private Point2D getCoord(final MouseEvent event) {
        return new Point2D(event.getScreenX(), event.getScreenY());
    }

    /**
     * Updates the score and lives labels.
     * @param stats
     *          to update the labels with
     */
    public void updateStats(final Pair<Integer, Integer> stats) {
        Platform.runLater(() -> {
            final String scoreLabel = String.valueOf(stats.getValue().intValue());
            final String livesLabel = String.valueOf(stats.getKey().intValue());
            this.lives.setText("Lives:" + livesLabel);
            this.score.setText("Score:" + scoreLabel);
        });
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
