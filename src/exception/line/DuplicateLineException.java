package exception.line;

public class DuplicateLineException extends LineException {

    public DuplicateLineException() {
        super();
    }

    public DuplicateLineException(String message) {
        super(message);
    }

    public DuplicateLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
