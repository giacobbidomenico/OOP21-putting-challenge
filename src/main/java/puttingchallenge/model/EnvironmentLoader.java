package puttingchallenge.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import javafx.geometry.Rectangle2D;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import puttingchallenge.common.Point2D;
import puttingchallenge.core.GameEngine;
import puttingchallenge.model.GameObject.GameObjectType;
import puttingchallenge.view.SceneType;

/**
 * Class the builds the {@link Environment} from its corresponding file.
 */
public final class EnvironmentLoader {

    private static final EnvironmentLoader SINGLETON = new EnvironmentLoader();

    private static final String SEP = File.separator + File.separator;
    private static final String PATH_START = System.getProperty("user.dir")
                                             + SEP + "res"
                                             + SEP + "scenes"
                                             + SEP;
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
     * @param controller
     *      the controller of the application
     * @param screenDimensions
     *       dimensions of the screen
     * @return
     *      the {@link Environment} related to the given tag
     * @throws IOException
     *      if the file is not loaded correctly
     */
    public Optional<Environment> getEnvironment(final SceneType sceneTag, 
                                                final GameEngine controller, 
                                                final Rectangle2D screenDimensions) throws IOException {
        if (sceneTag.isLevel()) {
            final String path = PATH_START + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END;
            final String jsonString = IOUtils.toString(new FileInputStream(path), "UTF-8");
            final JSONObject file = new JSONObject(jsonString);
            final BuilderEnvironment builder = new BuilderEnvironmentImpl();

            builder.controller(controller);
            builder.screenDimension(screenDimensions);
            this.setDimension(builder, file, screenDimensions);
            this.setBall(builder, file);
            this.setPlayer(builder, file);
            this.addObstacles(builder, file);
            return Optional.of(builder.build());
        } else {
            return Optional.empty();
        }
    }

    private void setDimension(final BuilderEnvironment builder, 
                              final JSONObject file,
                              final Rectangle2D dimensions) {
        file.getJSONObject("scene");
        final double w = dimensions.getWidth() / file.getDouble("hScale");
        final double h = dimensions.getHeight() / file.getDouble("wScale");
        builder.dimension(w, h);
    }

    private void addObstacles(final BuilderEnvironment builder, final JSONObject file) {
        final Iterator<Object> obstacles = file.getJSONArray("staticObstacles").iterator();
        while (obstacles.hasNext()) {
            final JSONObject obj = (JSONObject) obstacles.next();
            final GameObjectType type = GameObjectType.getFromIndex(obj.getInt("type")).get();
            final double x = obj.getDouble("posX");
            final double y = obj.getDouble("posY");
            final double w = obj.getDouble("weight");
            final double h = obj.getDouble("height");
            builder.addStaticObstacle(type, new Point2D(x, y), w, h);
        }
    }

    private void setPlayer(final BuilderEnvironment builder, final JSONObject file) {
        final JSONObject player = file.getJSONObject("player");
        final double x = player.getDouble("posX");
        final double y = player.getDouble("posY");
        builder.player(new Point2D(x, y));
    }

    private void setBall(final BuilderEnvironment builder, final JSONObject file) {
        final JSONObject ball = file.getJSONObject("ball");
        final double x = ball.getDouble("posX");
        final double y = ball.getDouble("posY");
        final double radius = ball.getDouble("radius");
        builder.ball(new Point2D(x, y), radius);
    }

}
