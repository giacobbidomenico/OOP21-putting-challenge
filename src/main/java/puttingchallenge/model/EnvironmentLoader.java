package puttingchallenge.model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import javafx.geometry.Rectangle2D;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import puttingchallenge.common.Point2D;
import puttingchallenge.model.gameobjects.GameObject.GameObjectType;
import puttingchallenge.view.SceneType;

/**
 * Class the builds the {@link Environment} from its corresponding file.
 */
public final class EnvironmentLoader {

    private static final EnvironmentLoader SINGLETON = new EnvironmentLoader();

    private static final String WSCALE = "wScale";
    private static final String HSCALE = "hScale";
    private static final String POSX = "posX";
    private static final String POSY = "posY";
    private static final String SEP = File.separator;
    private static final String PATH_START = "/scenes/";
    private static final String PATH_END = ".json";


    /**
     * Returns the single instance of the {@link EnvironmentLoader}.
     * 
     * @return
     *      the instance.
     */
    public static EnvironmentLoader getLoader() {
        return SINGLETON;
    }

    /**
     * Loads the appropriate environment.
     * 
     * @param sceneTag
     *      the {@link SceneType} related to the environment to be loaded
     * @return
     *      the {@link Environment} related to the given tag
     * @throws IOException
     *      if the file is not loaded correctly
     */
    public Optional<Environment> getEnvironment(final SceneType sceneTag) throws IOException {
        if (sceneTag.isLevel()) {
            final String path = PATH_START + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END;
            final String jsonString = IOUtils.toString(getClass().getResource(path), "UTF-8");
            final JSONObject file = new JSONObject(jsonString);
            final BuilderEnvironment builder = new BuilderEnvironmentImpl();
            final JSONObject  dimScene = file.getJSONObject("scene");
            final Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
            final double w = screenDim.getWidth() * (dimScene.getDouble(WSCALE) / 100);
            final double h = screenDim.getHeight() * (dimScene.getDouble(HSCALE) / 100);
            builder.container(new Rectangle2D(0, 0, w, h));
            this.setBall(w, h, builder, file);
            this.setPlayer(w, h, builder, file);
            this.setHole(w, h, builder, file);
            this.addObstacles(w, h, builder, file);
            return Optional.of(builder.build());
        } else {
            return Optional.empty();
        }
    }

    private void setHole(final double w,
            final double h,
            final BuilderEnvironment builder, 
            final JSONObject file) {
        final JSONObject hole = file.getJSONObject("hole");
        final double x = w * (hole.getDouble(POSX) / 100);
        final double y = h * (hole.getDouble(POSY) / 100);
        final double wPerc = w * (hole.getDouble(WSCALE) / 100);
        final double hPerc = h * (hole.getDouble(HSCALE) / 100);
        builder.hole(new Point2D(x, y), wPerc, hPerc);
    }

    private void addObstacles(final double w, 
                              final double h, 
                              final BuilderEnvironment builder, 
                              final JSONObject file) {
        final Iterator<Object> obstacles = file.getJSONArray("staticObstacles").iterator();
        while (obstacles.hasNext()) {
            final JSONObject obj = (JSONObject) obstacles.next();
            final GameObjectType type = GameObjectType.valueOf(obj.getString("type"));
            final double x = w * (obj.getDouble(POSX) / 100);
            final double y = h * (obj.getDouble(POSY) / 100);
            final double wPerc = w * (obj.getDouble(WSCALE) / 100);
            final double hPerc = h * (obj.getDouble(HSCALE) / 100);
            builder.addStaticObstacle(type, new Point2D(x, y), new Rectangle2D(0, 0, wPerc, hPerc));
        }
    }

    private void setPlayer(final double w,
                           final double h,
                           final BuilderEnvironment builder, final JSONObject file) {
        final JSONObject player = file.getJSONObject("player");
        final double x = w * (player.getDouble(POSX) / 100);
        final double y = h * (player.getDouble(POSY) / 100);
        final double wPerc = w * (player.getDouble(WSCALE) / 100);
        final double hPerc = h * (player.getDouble(HSCALE) / 100);
        final String path = player.getString("skinPath");
        builder.player(new Point2D(x, y), path, wPerc, hPerc);
    }

    private void setBall(final double w,
                         final double h,
                         final BuilderEnvironment builder, 
                         final JSONObject file) {
        final JSONObject ball = file.getJSONObject("ball");
        final double x = w * (ball.getDouble(POSX) / 100);
        final double y = h * (ball.getDouble(POSY) / 100);
        final double radius = w * (ball.getDouble("radiusScale") / 100);
        builder.ball(new Point2D(x, y), radius);
    }

}
