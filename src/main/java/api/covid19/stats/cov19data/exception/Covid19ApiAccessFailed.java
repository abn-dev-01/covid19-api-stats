package api.covid19.stats.cov19data.exception;

public class Covid19ApiAccessFailed extends RuntimeException {

    public Covid19ApiAccessFailed(String message) {
        super(message);
    }

    public Covid19ApiAccessFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public Covid19ApiAccessFailed(Throwable cause) {
        super(cause);
    }
}
