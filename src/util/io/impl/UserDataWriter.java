package util.io.impl;

import models.user.User;
import util.io.Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
/**
 * Writer implementation for {@link User}
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see User
 */
public class UserDataWriter implements Writer<User> {

    /**
     * Writes {@link Collection} of data in file is located in path
     * @param data data for writing
     * @param strPath file path
     * @param <T> type of data
     * @throws IOException if any problem with writing exists
     */
    @Override
    public <T extends User> void write(Collection<T> data, String strPath) throws IOException {
        Path path = Paths.get(strPath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        for (T user : data) {
            Files.writeString(path, user.toString() + "\n", StandardOpenOption.APPEND);
        }
    }
}
