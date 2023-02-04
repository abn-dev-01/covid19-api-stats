package api.covid19.stats.exceptions;

public class InvalidParameterRtException extends RuntimeException {

    public InvalidParameterRtException(String message) {
        super(message);
    }

    public InvalidParameterRtException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterRtException(Throwable cause) {
        super(cause);
    }
}
