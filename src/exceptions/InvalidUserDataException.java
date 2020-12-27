package exceptions;
/**
 * Signals that user credentials or personal data is invalid.
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class InvalidUserDataException extends InvalidPropertyException {

    public InvalidUserDataException(String message) {
        super(message);
    }
}
