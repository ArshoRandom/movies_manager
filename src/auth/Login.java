package auth;

import config.AppConfig;
import exceptions.ModelNotFoundException;
import models.movies.base.AbstractMovie;
import models.user.User;
import services.UserService;
import util.cache.MovieCache;
import util.cache.Session;
import util.digest.DigestUtils;
import util.helpers.KeyPair;
import util.io.impl.MovieDataReader;
import util.io.impl.UserDataReader;
import util.mapper.Mapper;
import util.mapper.MovieMapper;
import util.mapper.UserMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 *
 * Class is for {@link User} logged in processing
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class Login {

    private Login(){}

    /**
     * if {@link User} exists by username and password than it saves in {@link Session}
     * @param username username for check
     * @param password password for check
     * @throws ModelNotFoundException if user with username and password not exists
     * @exception RuntimeException if contains any problem with user data reading
     */
    public static void login(String username, String password) throws ModelNotFoundException {
        UserDataReader reader = new UserDataReader();
        try {
            List<String> read = reader.read(AppConfig.userDataPath);
            UserMapper mapper = new UserMapper();
            Map<KeyPair, User> userMap = mapper.map(read);
            User user = userMap.get(new KeyPair<>(username, DigestUtils.md5(password)));
            if (Objects.nonNull(user)) {
                Session.setCurrentUser(user);
                loadUserMovies(username);
                UserService.printUserInfo(user);
            } else {
                throw new ModelNotFoundException("invalid username or password");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads {@link User} movies in {@link MovieCache} using user username
     * @param username username for loading
     * @throws IOException if contains any problem with user data reading
     */
    private static void loadUserMovies(String username) throws IOException {
        MovieDataReader reader = new MovieDataReader();
        Mapper<KeyPair, AbstractMovie> movieMapper = new MovieMapper();
        File file = new File(AppConfig.getMoviesDataPathFor(username));
        if (file.exists()) {

            Map<KeyPair, AbstractMovie> movieMap = movieMapper.map(reader.read(file.getPath()));
            MovieCache.setCache(movieMap);

        } else {
            System.err.println("You dont have films");
        }
    }
}
