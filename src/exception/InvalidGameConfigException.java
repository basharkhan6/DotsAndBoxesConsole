package exception;

public class InvalidGameConfigException extends RuntimeException {

    public InvalidGameConfigException() {
    }

    public InvalidGameConfigException(String message) {
        super(message);
    }

    public InvalidGameConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
