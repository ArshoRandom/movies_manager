package exceptions;
/**
 * Signals that the command is invalid.
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String command) {
        super("Invalid command : " + command);
    }
}
