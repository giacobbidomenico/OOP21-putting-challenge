package puttingchallenge.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import puttingchallenge.model.GameObject;

/**
 * Class that defines the controller that manages the {@link Scene} of the view.
 *
 */
public class SceneControllerImpl implements SceneController {

    private final Canvas canvas;
    private final List<GameObject> gameObjects;
    private Optional<Scene> currentScene;

    /**
     * Build a new {@link SceneControllerImpl}.
     * 
     */
    public SceneControllerImpl() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final var width = dim.getWidth();
        final var height = dim.getHeight();
        this.canvas = new Canvas(width / 2, height / 2);
        this.gameObjects = new LinkedList<>();
        this.currentScene = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    public void setScene(final List<GameObject> newGameObjects) {
        this.gameObjects.clear();
        this.gameObjects.addAll(newGameObjects);
        final Group root = new Group();
        this.currentScene = Optional.of(new Scene(root));
        root.getChildren().add(this.canvas);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Scene> getScene() {
        return this.currentScene;
    }

    /**
     * {@inheritDoc}
     */
    public void render() {
        this.gameObjects.stream()
            .peek(e -> e.draw(this.canvas.getGraphicsContext2D()));
    }
}
