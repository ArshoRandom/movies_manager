package exceptions;
/**
 * Signals that a property is invalid.
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class InvalidPropertyException extends RuntimeException {

    public InvalidPropertyException(String message) {
        super("Invalid property : " + message);
    }

    public static void check(boolean condition, String message) {
        if (condition) throw new InvalidPropertyException(message);
    }

}
