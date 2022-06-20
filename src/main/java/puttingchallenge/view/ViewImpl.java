package puttingchallenge.view;

import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Class that implements the view.
 */
public class ViewImpl implements View {

    private final GameEngine controller;
    private final Stage stage;
    private SceneController scene;

    /**
     * Build a new {@link ViewImpl}.
     * 
     * @param controller
     *          {@link GameEngine}, the main controller of the application
     * @param stage
     *          the primary stage of the JavaFX application
     */
    public ViewImpl(final GameEngine controller, final Stage stage) {
        this.controller = Objects.requireNonNull(controller);
        this.stage = Objects.requireNonNull(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildView() {
        this.loadScene(SceneType.MAIN_MENU);
        this.stage.setScene(scene.getScene());
        this.stage.sizeToScene();
        this.stage.setResizable(false);
        this.stage.show();
    }
    /**
     * Handles mouse pressed event.
     * @param event
     */
    private void handleMousePressed(final MouseEvent event) {
        this.controller.startAiming(event.getSceneX(), event.getSceneY());
        event.consume();
    }
    /**
     * Handles mouse released event.
     * @param event
     */
    private void handleMouseReleased(final MouseEvent event) {
        this.controller.shoot(event.getSceneX(), event.getSceneY());
        event.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadScene(final SceneType typeScene) {
        try {
            final List<GameObject> objs = this.controller.getEnv().getObjecs();
            this.scene = SceneLoader.getLoader().getScene(typeScene, objs, this);
            this.stage.setScene(scene.getScene());
            this.scene.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
            this.scene.getScene().addEventFilter(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> this.scene.render());
    }

}
