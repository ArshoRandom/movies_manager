package util.io.impl;

import models.movies.base.AbstractMovie;
import util.io.Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class MovieDataWriter implements Writer<AbstractMovie> {


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
