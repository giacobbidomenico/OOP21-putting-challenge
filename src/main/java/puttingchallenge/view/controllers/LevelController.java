package puttingchallenge.view.controllers;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.GameEventWithDetailsImpl;
import puttingchallenge.model.gameobjects.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} related to the loaded game level.
 * 
 */
public class LevelController extends AbstractSceneController {

    private static final double UPPER_LEFT_X = 0.0;
    private static final double UPPER_LEFT_Y = 0.0;

    private GraphicsContext gc;
    private String background;
    private boolean isAiming;

    /**
     * Initialize a new {@link LevelController}.
     * 
     * @param scene
     *            {@link Scene} relating to the loaded game level.
     * @param gameObjects
     *            {@link GameObject} present in the {@link Scene}
     * @param gc
     *            the {@link GraphicsContext} in which the object has to be drawn
     * @param background
     */
    public void init(final Scene scene,
                     final List<GameObject> gameObjects,
                     final GraphicsContext gc,
                     final String background) {
        super.init(scene, gameObjects);
        this.gc = gc;
        this.background = background;
        this.isAiming = false;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
        final Image backgrounImage = new Image(background);
        this.gc.drawImage(backgrounImage, 
                          UPPER_LEFT_X, 
                          UPPER_LEFT_Y, 
                          super.getScene().getWidth(), 
                          super.getScene().getHeight());
        this.gc.clearRect(UPPER_LEFT_X,
                          UPPER_LEFT_Y, 
                          super.getScene().getWidth(), 
                          super.getScene().getHeight());
        super.getGameObjects().stream().peek(e -> e.draw(gc));
    }
    /**
     * Handles mouse moved event.
     * @param event
     */
    @FXML
    public void handleMouseMoved(final MouseEvent event) {
        if (this.isAiming) {
            final Pair<Double, Double> coord = this.getCoord(event);
//            final GameEventWithDetailsImpl<Pair<Double, Double>> movingEvent = new GameEventWithDetailsImpl<>(GameEventType.MOVING, coord);
//            this.getMediator().notifyColleagues(movingEvent, this);
        }
    }
    /**
     * Handles mouse pressed event.
     * @param event
     */
    @FXML
    public void handleMousePressed(final MouseEvent event) {
        this.isAiming = true;
        final GameEventWithDetailsImpl<Pair<Double, Double>> movingEvent = new GameEventWithDetailsImpl<>(GameEventType.AIM, this.getCoord(event));
        this.getMediator().notifyColleagues(movingEvent, this);
    }
    /**
     * Handles mouse released event.
     * @param event
     */
    @FXML
    public void handleMouseReleased(final MouseEvent event) {
        this.isAiming = false;
        final GameEventWithDetailsImpl<Pair<Double, Double>> shootingEvent = new GameEventWithDetailsImpl<>(GameEventType.SHOOT, this.getCoord(event));
        this.getMediator().notifyColleagues(shootingEvent, this);
    }
    /**
     * Method that handle the action on the quit button.
     * @param event
     */
    @FXML
    public void handleQuit(final ActionEvent event) {
        final GameEventImpl quitEvent = new GameEventImpl(GameEventType.SHOW_MAIN_MENU);
        this.getMediator().notifyColleagues(quitEvent, this);
    }

    private Pair<Double, Double> getCoord(final MouseEvent event) {
        return new Pair<>(event.getScreenX(), event.getSceneY());
    }
}
