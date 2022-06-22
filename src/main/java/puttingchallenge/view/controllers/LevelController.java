package puttingchallenge.view.controllers;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import puttingchallenge.model.events.Mediator;
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
     * Handles mouse pressed event.
     * @param event
     */
    @FXML
    public void handleMousePressed(final MouseEvent event) {
        // TODO
    }
    /**
     * Handles mouse released event.
     * @param event
     */
    @FXML
    public void handleMouseReleased(final MouseEvent event) {
        // TODO
    }
    /**
     * Method that handle the action on the quit button.
     * @param event
     */
    @FXML
    public void handleQuit(final ActionEvent event) {
        //TODO
    }
}
