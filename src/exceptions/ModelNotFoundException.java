package exceptions;
/**
 * Signals that  models does not exist.
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class ModelNotFoundException extends Exception {

    public ModelNotFoundException(String modelName) {
        super("Model not found : " + modelName);
    }
}
