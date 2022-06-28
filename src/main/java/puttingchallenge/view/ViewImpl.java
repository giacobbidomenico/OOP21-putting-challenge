package puttingchallenge.view;

import puttingchallenge.model.events.GameEvent;
import puttingchallenge.model.events.GameEventImpl;
import puttingchallenge.model.events.GameEventType;
import puttingchallenge.model.events.Mediator;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.controllers.LevelController;
import puttingchallenge.view.controllers.SceneController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.scene.image.Image;
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
        this.stage.getIcons().add(new Image("/skins/icon.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildView() {
        Platform.runLater(() -> {
            this.loadScene(SceneType.MAIN_MENU, Collections.emptyList());
            this.stage.setScene(scene.getScene());
            this.stage.sizeToScene();
            this.stage.setResizable(false);
            this.stage.setOnCloseRequest(
                    (e) -> this.mediator.notifyColleagues(new GameEventImpl(GameEventType.QUIT), this)
            );
            this.stage.show();
        });
    }

    private void loadScene(final SceneType typeScene, final List<GameObject> objs) {
        try {
            this.mediator.removeColleague(scene);
            this.scene = SceneLoader.getLoader().getScene(typeScene, objs);
            this.mediator.addColleague(scene);
            scene.setMediator(mediator);
            Platform.runLater(() -> {
                this.stage.setScene(scene.getScene());
            });
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
