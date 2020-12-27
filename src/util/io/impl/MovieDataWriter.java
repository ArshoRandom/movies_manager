package util.io.impl;

import models.movies.base.AbstractMovie;
import util.io.Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
/**
 * Writer implementation for {@link models.movies.base.Movie}
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see models.movies.base.Movie
 */
public class MovieDataWriter implements Writer<AbstractMovie> {

    /**
     * Writes {@link Collection} of data in file is located in path
     * @param data data for writing
     * @param path file path
     * @param <T> type of data
     * @throws IOException if any problem with writing exists
     */
    @Override
    public <T extends AbstractMovie> void write(Collection<T> data, String path) throws IOException {
        if (data.isEmpty()) {
            System.out.println("You dont have films");
            return;
        }
        Files.deleteIfExists(Paths.get(path));
        Files.createFile(Paths.get(path));
        for (T movie : data) {
            Files.writeString(
                    Paths.get(path),
                    String.format("%s\n", movie.toString()),
                    StandardOpenOption.APPEND);
        }
    }
}
