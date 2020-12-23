package util.io.impl;

import util.io.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserDataReader implements Reader {

    @Override
    public List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }
}
