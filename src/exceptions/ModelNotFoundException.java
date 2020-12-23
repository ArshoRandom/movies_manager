package exceptions;

public class ModelNotFoundException extends Exception {

    public ModelNotFoundException(String modelName) {
        super("Model not found : " + modelName);
    }
}
