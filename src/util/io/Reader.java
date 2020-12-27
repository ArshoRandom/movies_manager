package util.io;

import java.io.IOException;
import java.util.List;
/**
 * Base interface for readers
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see util.io.impl.UserDataReader
 * @see util.io.impl.MovieDataReader
 */
public interface Reader {

    /**
     * Returns {@link List} of read data from file located in path
     * @param path file path
     * @return {@link List} of read lines
     * @throws IOException if any problem with reading exists
     */
    List<String> read(String path) throws IOException;
}
