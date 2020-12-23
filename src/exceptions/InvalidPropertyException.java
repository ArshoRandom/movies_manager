package exceptions;

public class InvalidPropertyException extends RuntimeException {

    public InvalidPropertyException(String message) {
        super("Invalid property : " + message);
    }

    public static void check(boolean condition, String message) {
        if (condition) throw new InvalidPropertyException(message);
    }

}
