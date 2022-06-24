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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import puttingchallenge.model.gameobjects.GameObject;
import puttingchallenge.view.controllers.LevelController;
import puttingchallenge.view.controllers.SceneController;

/**
 * Loads the given {@link SceneType} from its corresponding file.
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();

    private static final String SEP = File.separator;
    private static final String PATH_START = System.getProperty("user.dir")
                                             + SEP + "res"
                                             + SEP + "scenes"
                                             + SEP;
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
        final String path = "/" + "scenes" + "/" + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END_SCREEN;
        String path1 = "/scenes/main_menu.fxml";
        System.out.println(path);
        var a = getClass().getResource(path);
        final FXMLLoader loader = new FXMLLoader(a);
        //final Parent parent = loader.load();
        final VBox vbox = loader.load();
        //loader.setController();
        //final Parent parent = FXMLLoader.load(getClass().getResource(path));
        //parent = loader.load(new FileInputStream(path));


        final SceneController sc = loader.getController();
        sc.init(new Scene(vbox), objs);
        return sc;
    }

    private SceneController loadGameLevel(final SceneType sceneTag,
                                          final List<GameObject> objs) throws IOException {
        String path = PATH_START + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END_LEVEL;
        final String jsonString = IOUtils.toString(new FileInputStream(path), "UTF-8");
        final JSONObject jsonObj = new JSONObject(jsonString).getJSONObject("scene");
        final String background = jsonObj.getString("background");
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final double h = dim.getHeight() * (jsonObj.getDouble("wScale") / 100);
        final double w = dim.getWidth() * (jsonObj.getDouble("hScale") / 100);

        final FXMLLoader loader = new FXMLLoader();
        path = PATH_START + PATH_LEVELS + PATH_END_SCREEN;
        final Parent parent = loader.load(new FileInputStream(path));

        final Group root = new Group();
        final Scene scene = new Scene(root, w, h);
        final Canvas canvas = new Canvas(w, h);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        root.getChildren().add(parent);
        gc.drawImage(new Image(background), 0, 0, w, h);

        final LevelController sc = loader.getController();
        sc.init(scene, objs, gc, background);
        return sc;
    }
}

