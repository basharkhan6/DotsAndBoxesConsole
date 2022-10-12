package exception;

public class PlayersFullException extends RuntimeException {

    public PlayersFullException() {
    }

    public PlayersFullException(String message) {
        super(message);
    }

    public PlayersFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
