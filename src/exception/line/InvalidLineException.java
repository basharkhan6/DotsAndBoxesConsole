package exception.line;

public class InvalidLineException extends LineException {

    public InvalidLineException() {
    }

    public InvalidLineException(String message) {
        super(message);
    }

    public InvalidLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
