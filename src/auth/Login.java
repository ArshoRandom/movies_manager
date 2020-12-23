package auth;

import exceptions.InvalidDataFormatException;
import exceptions.ModelNotFoundException;
import models.movies.base.AbstractMovie;
import models.user.User;
import services.UserService;
import util.cache.MovieCache;
import util.cache.UserCache;
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

public class Login {

    public static void login(String username, String password) throws ModelNotFoundException {
        UserDataReader reader = new UserDataReader();
        try {
            List<String> read = reader.read("src/resources/users.txt");
            UserMapper mapper = new UserMapper();
            Map<KeyPair, User> userMap = mapper.map(read);
            User user = userMap.get(new KeyPair<>(username, DigestUtils.md5(password)));
            if (Objects.nonNull(user)) {
                UserCache.setCurrentUser(user);
                loadUserMovies(username);
                UserService.printUserInfo(user);
            } else {
                throw new ModelNotFoundException("invalid username or password");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadUserMovies(String username) throws IOException {
        MovieDataReader reader = new MovieDataReader();
        Mapper<KeyPair, AbstractMovie> movieMapper = new MovieMapper();
        File file = new File(String.format("src/resources/%s.txt", username));
        if (file.exists()) {

            Map<KeyPair, AbstractMovie> movieMap = movieMapper.map(reader.read(file.getPath()));
            MovieCache.setCache(movieMap);

        } else {
            System.err.println("You dont have films");
        }
    }
}
