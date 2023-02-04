package api.covid19.stats.exceptions;

public class InvalidRequestRtException extends RuntimeException {

    public InvalidRequestRtException(String message) {
        super(message);
    }

    public InvalidRequestRtException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestRtException(Throwable cause) {
        super(cause);
    }
}
