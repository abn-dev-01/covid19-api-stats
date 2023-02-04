package api.covid19.stats.cov19data.exception;

public class Covid19ApiRtException extends RuntimeException {

    public Covid19ApiRtException(String message) {
        super(message);
    }

    public Covid19ApiRtException(String message, Throwable cause) {
        super(message, cause);
    }

    public Covid19ApiRtException(Throwable cause) {
        super(cause);
    }
}
