package api.covid19.stats.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class AbstractCustomizedExceptionHandler
    extends ResponseEntityExceptionHandler {

    /**
     * 500 INTERNAL_ERROR
     *
     * @param ex
     * @param request
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse exceptionResponse = new ExceptionResponse(status.value(),
                                                                    new Date(),
                                                                    ex.getMessage(),
                                                                    request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
