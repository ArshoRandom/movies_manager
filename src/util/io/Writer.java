package util.io;

import java.io.IOException;
import java.util.Collection;

public interface Writer<S> {

   <T extends S> void write(Collection<T> data, String path) throws IOException;

}
