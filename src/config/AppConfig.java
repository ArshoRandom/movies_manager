package config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {

    public static String userDataPath;
    private static String moviesDataPath;
    private static final String PATH_TO_RESOURCES;

    private AppConfig() {
    }

    public static String getMoviesDataPathFor(String username) {
        return String.format(moviesDataPath, username);
    }

    static {
        PATH_TO_RESOURCES = String.join(File.separator,"src" ,"resources","app.properties");
        init();
    }

    private static void init() {
        try {
            FileReader reader = new FileReader(PATH_TO_RESOURCES);
            Properties properties = new Properties();
            properties.load(reader);
            userDataPath = getUniversalPath(properties.getProperty("path.user"));
            moviesDataPath = getUniversalPath(properties.getProperty("path.movies"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getUniversalPath(String path) {

        if (systemIsWindows()) {
           return Paths.get(path.replace("\\", File.separator)).toAbsolutePath().toString();
        }
        return Paths.get(path.replace("/", File.separator)).toAbsolutePath().toString();
    }

    private static boolean systemIsWindows() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().contains("windows");
    }
}
