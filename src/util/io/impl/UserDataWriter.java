package util.io.impl;

import models.user.User;
import util.io.Writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class UserDataWriter implements Writer<User> {

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
