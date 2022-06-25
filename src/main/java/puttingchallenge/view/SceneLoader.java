package puttingchallenge.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.controllers.LevelController;
import puttingchallenge.view.controllers.SceneController;

/**
 * Loads the given {@link SceneType} from its corresponding file.
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();

    private static final String SEP = File.separator;
    private static final String PATH_START_LEVEL = System.getProperty("user.dir") + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "scenes" + SEP;
    private static final String PATH_START_SCREEN = "/scenes/";
    private static final String PATH_END_SCREEN = ".fxml";
    private static final String PATH_END_LEVEL = ".json";
    private static final String PATH_LEVELS = "levels";

    /**
     * Returns the single instance of the {@link SceneLoader}.
     * 
     * @return
     *      the instance.
     */
    public static SceneLoader getLoader() {
        return SINGLETON;
    }

    /**
     * Loads the given scene.
     * 
     * @param sceneTag
     *      the {@link SceneType} to be loaded
     * @param objs
     *      a {@link List} of the game objects of the scene
     * @return
     *      the {@link SceneController} related to the given tag
     * @throws IOException
     *      if the file is not loaded correctly
     */
    public SceneController getScene(final SceneType sceneTag,
                                    final List<GameObject> objs) throws IOException {
        if (sceneTag.isLevel()) {
            return this.loadGameLevel(sceneTag, objs);
        } else {
            return this.loadScreen(sceneTag, objs);
        }
    }

    private SceneController loadScreen(final SceneType sceneTag,
                                       final List<GameObject> objs) throws IOException {
        final String path = PATH_START_SCREEN + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END_SCREEN;
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        final VBox vbox = loader.load();

        final SceneController sc = loader.getController();
        sc.init(new Scene(vbox), objs);
        return sc;
    }

    private SceneController loadGameLevel(final SceneType sceneTag,
                                          final List<GameObject> objs) throws IOException {
        final String path = PATH_START_LEVEL + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END_LEVEL;
        final LevelController levelController = new LevelController();
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final String jsonString = IOUtils.toString(new FileInputStream(path), "UTF-8");
        final JSONObject jsonObj = new JSONObject(jsonString).getJSONObject("scene");
        final double h = dim.getHeight() * (jsonObj.getDouble("wScale") / 100);
        final double w = dim.getWidth() * (jsonObj.getDouble("hScale") / 100);
        final Button button = new Button("Quit");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, levelController);
        final var posWButton = w * (0.1);
        button.setLayoutX(w - posWButton);
        final Label score = new Label("Score");
        final var posWScore = w * (0.3);
        score.setLayoutX(posWScore);
        final Label lives = new Label("Lives");
        final var posWLives = w * (0.4);
        score.setLayoutX(posWLives);
        final AnchorPane layout = new AnchorPane();
        layout.getChildren().add(button);
        layout.getChildren().add(score);
        layout.getChildren().add(lives);
        layout.addEventHandler(MouseEvent.MOUSE_MOVED, levelController);
        layout.addEventHandler(MouseEvent.MOUSE_PRESSED, levelController);
        layout.addEventHandler(MouseEvent.MOUSE_RELEASED, levelController);
        final Scene scene = new Scene(layout, w, h);
        levelController.init(scene, objs);
        return levelController;
    }
}

