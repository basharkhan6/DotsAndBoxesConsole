package exception.line;

public class ReverseLineException extends LineException {

    public ReverseLineException() {
    }

    public ReverseLineException(String message) {
        super(message);
    }

    public ReverseLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
