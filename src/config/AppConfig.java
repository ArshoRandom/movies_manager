package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    public static String userDataPath;
    private static String moviesDataPath;

    private AppConfig(){}

    public static String getMoviesDataPathFor(String username){
        return String.format(moviesDataPath,username);
    }

    static {
        init();
    }

    private static void init(){
        try {
            FileReader reader = new FileReader("src/resources/app.properties");
            Properties properties =new Properties();
            properties.load(reader);
            userDataPath = properties.getProperty("path.user");
            moviesDataPath = properties.getProperty("path.movies");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
