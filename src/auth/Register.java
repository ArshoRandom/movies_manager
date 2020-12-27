package auth;

import config.AppConfig;
import models.user.User;
import util.cache.Session;
import util.io.impl.UserDataReader;
import util.io.impl.UserDataWriter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * Class is for {@link User} registration processing
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */

public class Register {

    private Register(){}

    /**
     * Saves {@link User} in file and {@link Session}
     * @param user {@link User} instance for saving
     * @exception RuntimeException if user already exists (checks with username)
     */
    public static void register(User user) {
        checkDuplicate(user.getUsername());
        UserDataWriter writer = new UserDataWriter();
        try {
            Session.setCurrentUser(user);
            writer.write(Collections.singleton(user), AppConfig.userDataPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks duplicates in user data file using username
     * @param username username for checking
     * @throws RuntimeException if user already exists
     */
    private static void checkDuplicate(String username) {
        UserDataReader reader = new UserDataReader();
        File file = new File(AppConfig.userDataPath);
        if (file.exists()) {
            try {
                List<String> list = reader.read(file.getPath());
                for (String line : list) {
                    if (line.startsWith(username + " ,")) {
                        throw new RuntimeException("User with username = " + username + " already exists");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
