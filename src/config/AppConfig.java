package config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * Class is for configuration of application
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class AppConfig {

    public static String userDataPath;
    private static String moviesDataPath;
    private static final String PATH_TO_RESOURCES;

    private AppConfig() {
    }

    /**
     * Returns path to saved movies for {@link models.user.User} using username
     * @param username for find path
     * @return path to user movies
     */
    public static String getMoviesDataPathFor(String username) {
        return String.format(moviesDataPath, username);
    }

    static {
        PATH_TO_RESOURCES = String.join(File.separator,"src" ,"resources","app.properties");
        init();
    }

    /**
     * Initialize paths through {@link Properties} and app.properties
     */
    private static void init() {
        try {
            FileReader reader = new FileReader(PATH_TO_RESOURCES);
            Properties properties = new Properties();
            properties.load(reader);
            userDataPath = getUniversalPath(properties.getProperty("path.user"));
            moviesDataPath = getUniversalPath(properties.getProperty("path.movies"));

            Path dbMoviesPath = Paths.get(String.join(File.separator,"src" ,"resources","database","movies"));
            Path dbUsersPath = Paths.get(String.join(File.separator,"src" ,"resources","database","users"));
            if (Files.notExists(dbMoviesPath)){
                Files.createDirectories(dbMoviesPath);
            }
            if (Files.notExists(dbUsersPath)){
                Files.createDirectories(dbUsersPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns path adapted by OS
     * @param path unadapted path
     * @return adapted path
     */
    private static String getUniversalPath(String path) {

        if (systemIsWindows()) {
           return Paths.get(path.replace("\\", File.separator)).toAbsolutePath().toString();
        }
        return Paths.get(path.replace("/", File.separator)).toAbsolutePath().toString();
    }

    /**
     * Returns {@code true} if OS is windows, otherwise returns {@code false}
     * @return true if OS is Windows
     */
    private static boolean systemIsWindows() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().contains("windows");
    }
}
