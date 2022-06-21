package puttingchallenge.view;

import puttingchallenge.core.GameEngine;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.controllers.SceneController;

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
     * {@inheritDoc}
     */
    @Override
    public void loadScene(final SceneType typeScene) {
        try {
            final List<GameObject> objs = this.controller.getEnv().getObjecs();
            this.scene = SceneLoader.getLoader().getScene(typeScene, objs, this);
            this.stage.setScene(scene.getScene());
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
