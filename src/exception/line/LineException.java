package exception.line;

public class LineException extends RuntimeException {

    public LineException() {
    }

    public LineException(String message) {
        super(message);
    }

    public LineException(String message, Throwable cause) {
        super(message, cause);
    }
}
