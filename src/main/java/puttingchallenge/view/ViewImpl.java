package puttingchallenge.view;

import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.controllers.SceneController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Class that implements the view.
 */
public class ViewImpl implements View {

    private final Stage stage;
    private SceneController scene;
    private Mediator mediator;

    /**
     * Build a new {@link ViewImpl}.
     * 
     * @param stage
     *          the primary stage of the JavaFX application
     */
    public ViewImpl(final Stage stage) {
        this.stage = Objects.requireNonNull(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildView() {
        this.loadScene(SceneType.MAIN_MENU, Collections.emptyList());
        this.stage.setScene(scene.getScene());
        this.stage.sizeToScene();
        this.stage.setResizable(false);
        this.stage.show();
    }

    private void loadScene(final SceneType typeScene, final List<GameObject> objs) {
        try {
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
                final Pair<SceneType, List<GameObject>> wrapper = (Pair<SceneType, List<GameObject>>) event.getDetails().get();
                this.loadScene(wrapper.getKey(), wrapper.getValue());
                break;
            default:
                break;
        }
    }

}
