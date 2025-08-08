package CustomException;

/**
 * Custom exception thrown when the inputs given are not as expected.
 */
public class AppException extends Exception {
    public AppException(String msg) {
        super(msg);
    }
}
