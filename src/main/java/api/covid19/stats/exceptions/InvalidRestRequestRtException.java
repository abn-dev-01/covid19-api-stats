package api.covid19.stats.exceptions;

public class InvalidRestRequestRtException extends RuntimeException {

    public InvalidRestRequestRtException(String message) {
        super(message);
    }

    public InvalidRestRequestRtException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRestRequestRtException(Throwable cause) {
        super(cause);
    }
}
