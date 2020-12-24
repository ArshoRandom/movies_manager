package auth;

import config.AppConfig;
import models.user.User;
import util.cache.UserCache;
import util.io.impl.UserDataReader;
import util.io.impl.UserDataWriter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Register {

    private Register(){}

    public static void register(User user) {
        checkDuplicate(user.getUsername());
        UserDataWriter writer = new UserDataWriter();
        try {
            UserCache.setCurrentUser(user);
            writer.write(Collections.singleton(user), AppConfig.userDataPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
