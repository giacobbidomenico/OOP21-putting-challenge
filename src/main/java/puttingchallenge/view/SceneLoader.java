package puttingchallenge.view;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import puttingchallenge.model.GameObject;

/**
 * Loads the given {@link SceneType} from its corresponding file.
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();
    private static final String PATH_START = "/scenes/";
    private static final String PATH_END_SCREEN = ".fxml";
    private static final String PATH_END_LEVEL = ".json";

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
     * @param view
     *      the view of the application
     * @return
     *      the {@link SceneController} related to the given tag
     * @throws IOException
     *      if the file is not loaded correctly
     */
    public SceneController getScene(final SceneType sceneTag,
                                    final List<GameObject> objs,
                                    final View view) throws IOException {
        if (sceneTag.isLevel()) {
            return this.loadGameLevel(sceneTag, objs, view);
        } else {
            return this.loadScreen(sceneTag, objs, view);
        }
    }

    private SceneController loadScreen(final SceneType sceneTag,
                                       final List<GameObject> objs,
                                       final View view) {
        final FXMLLoader loader = new FXMLLoader();
        final String path = PATH_START + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END_SCREEN;
        final Parent parent = loader.load(this.getClass().getResourceAsStream(path));

        final SceneController sc;
        switch (sceneTag) {
            case MAIN_MENU:
                sc = new MenuController(new Scene(parent), objs, view);
                break;
            case LEADEARBOARD:
                sc = new LeaderboardController(new Scene(parent), objs, view);
                break;
            case GAME_OVER:
                sc = new GameOverController(new Scene(parent), objs, view);
                break;
            default:
                break;
        }
        return sc;
    }

    private SceneController loadGameLevel(final SceneType sceneTag,
                                          final List<GameObject> objs,
                                          final View view) {
        final String path = PATH_START + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END_LEVEL;
        JSONObject jsonObj = new JSONObject(path).getJSONObject("scene");
        final String background = jsonObj.getString("background");
        final double h = Double.parseDouble(jsonObj.getString("height"));
        final double w = Double.parseDouble(jsonObj.getString("weight"));

        final Group root = new Group();
        final Scene scene = new Scene(root);
        final Canvas canvas = new Canvas(h, w);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        gc.drawImage(new Image(background), 0, 0, w, h);
        return new LevelController(scene, objs, view);
    }
}

