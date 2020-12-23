package util.io;

import java.io.IOException;
import java.util.List;

public interface Reader {

    List<String> read(String path) throws IOException;
}
