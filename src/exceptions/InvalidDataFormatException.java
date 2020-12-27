package exceptions;
/**
 * Signals that  data is invalid or not formatted.
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class InvalidDataFormatException extends RuntimeException{

    public InvalidDataFormatException(Throwable cause) {
        super(cause);
    }
}
