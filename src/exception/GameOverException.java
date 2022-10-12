package exception;

public class GameOverException extends RuntimeException {

    public GameOverException() {
        super();
    }

    public GameOverException(String message) {
        super(message);
    }

    public GameOverException(String message, Throwable cause) {
        super(message, cause);
    }
}
