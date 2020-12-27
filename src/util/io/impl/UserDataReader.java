package util.io.impl;

import util.io.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 *
 * Reader class for reading data from file
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class UserDataReader implements Reader {

    /**
     * Returns {@link List} of lines from file located in path
     * @param path file path
     * @return {@link List} of string lines
     * @throws IOException if any problem with reading exists
     */
    @Override
    public List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }
}
