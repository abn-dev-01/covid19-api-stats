package api.covid19.stats.exceptions;

public class CountryNotFound extends RuntimeException {

    public CountryNotFound(String message) {
        super(message);
    }

    public CountryNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryNotFound(Throwable cause) {
        super(cause);
    }
}
