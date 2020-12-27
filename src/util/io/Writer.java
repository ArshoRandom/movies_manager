package util.io;

import java.io.IOException;
import java.util.Collection;
/**
 *
 * Base interface for writers
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see util.io.impl.UserDataWriter
 * @see util.io.impl.MovieDataReader
 */
public interface Writer<S> {

   /**
    * Writes {@link Collection} of data in file is located in path
    * @param data data for writing
    * @param path file path
    * @param <T> type of data
    * @throws IOException if any problem with writing exists
    */
   <T extends S> void write(Collection<T> data, String path) throws IOException;

}
