package puttingchallenge.view;

import puttingchallenge.core.GameEngine;
import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;
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

    private final Stage stage;
    private SceneController scene;
    private Mediator mediator;
    private final GameEngine controller;

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

    private void loadScene(final SceneType typeScene) {
        try {
            final List<GameObject> objs = this.controller.getEnv().getObjects();
            this.scene = SceneLoader.getLoader().getScene(typeScene, objs, mediator);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediator(final Mediator mediator) {
       this.mediator = mediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent event) {
        switch (event.getEventType()) {
            case SET_SCENE:
                this.loadScene((SceneType) event.getDetails().get());
                break;
            default:
                break;
        }
    }

}
