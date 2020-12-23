package exceptions;

public class InvalidDataFormatException extends Exception{
    public InvalidDataFormatException(String message) {
        super("Invalid or corrupted file : " + message + " is not valid");
    }
}
