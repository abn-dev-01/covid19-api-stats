package api.covid19.stats.exceptions;

import java.util.Date;

/**
 * REPLACE THIS CLASS WITH IResult
 */
public class ExceptionResponse {
    private final int status;
    private final Date timestamp;
    private final String message;
    private final String details;

    public ExceptionResponse(int statusCode, Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return status;
    }

}
