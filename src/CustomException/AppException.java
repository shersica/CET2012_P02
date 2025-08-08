package CustomException;

/**
 * Custom exception thrown when the inputs given are not as expected.
 */
public class AppException extends Exception {
    /**
     * Constructor for custom exception
     * @param msg error message provided to the user
     */
    public AppException(String msg) {
        super(msg);
    }
}
