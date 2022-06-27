package puttingchallenge.core;

import java.io.File;

/**
 * Class that manages all files used.
 */
public class FileManager {

    private static final String SEPARATOR = System.getProperty("user.separator");

    private static final String USER_HOME = System.getProperty("user.home");

    /**
     * Path to PuttingChallenge files.
     */
    public static final String APP_DIRECTORY = USER_HOME 
            + SEPARATOR 
            + "PuttinChallenge";

    /**
     * Path to the leaderboard directory.
     */
    public static final String LEADERBOARD_DIRECTORY = APP_DIRECTORY 
            + SEPARATOR 
            + "Saves";

    /**
     * The file used to store leaderboard.
     */
    public static final String LEADERBOARD_FILE = LEADERBOARD_DIRECTORY 
            + SEPARATOR 
            + "leaderboard.txt";

    private FileManager() { }

    /**
     * Sets up file necessary to the application.
     * @return true if every file is created, false otherwise
     */
    public static boolean init() {
        File f = new File(LEADERBOARD_FILE);
        try {
            f.createNewFile();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
