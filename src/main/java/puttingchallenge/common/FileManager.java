package puttingchallenge.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that manages all files used.
 */
public final class FileManager {

    private static final String SEPARATOR = System.getProperty("file.separator");

    private static final String USER_HOME = System.getProperty("user.home");

    /**
     * Path to PuttingChallenge files.
     */
    public static final String APP_DIRECTORY = USER_HOME 
            + SEPARATOR 
            + "PuttingChallenge";

    /**
     * Path to the leaderboard directory.
     */
    public static final String STATS_DIRECTORY = APP_DIRECTORY 
            + SEPARATOR 
            + "Saves";

    /**
     * The file used to store statistics.
     */
    public static final String STATS_FILE = STATS_DIRECTORY 
            + SEPARATOR 
            + "stats.txt";

    /**
     * Relative path for game levels and application screens directory. 
     */
    public static final String PATH_START_SCENES = "/scenes/";

    /**
     * Extension of game levels files.
     */
    public static final String PATH_END_LEVEL = ".json";

    /**
     * Extension of application screens files.
     */
    public static final String PATH_END_SCREEN = ".fxml";

    /**
     * Path of general application skins.
     */
    public static final String GENERAL_SKINS_PATH = SEPARATOR + "skins" + SEPARATOR;

    /**
     * Path of game obstacles skins.
     */
    public static final String OBSTACLES_SKINS_PATH = GENERAL_SKINS_PATH
                                                      + SEPARATOR + "obstacles" + SEPARATOR;



    private FileManager() { }

    /**
     * @param file to delete
     */
    public static void deleteIfPresent(final String file) {
        final File f = new File(file);
        try {
            f.delete();
        } catch (SecurityException e) { 
            e.printStackTrace();
        }
    }

    /**
     * Sets up file necessary to the application.
     * @return true if every file is created, false otherwise
     */
    public static boolean init() {
        try {
            Files.createDirectories(Paths.get(APP_DIRECTORY));
            Files.createDirectories(Paths.get(STATS_DIRECTORY));
            if (!Files.exists(Paths.get(STATS_FILE))) {
                Files.createFile(Paths.get(STATS_FILE));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
