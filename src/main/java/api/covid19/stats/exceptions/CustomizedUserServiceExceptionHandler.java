package api.covid19.stats.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomizedUserServiceExceptionHandler extends AbstractCustomizedExceptionHandler {

//    @ExceptionHandler(UserAuthorizationTokenException.class)
//    public final ResponseEntity<Object> handleAuthByTokenInvalidException(Exception ex, WebRequest request) {
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        ExceptionResponse exceptionResponse = new ExceptionResponse(status.value(),
//                new Date(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(exceptionResponse, status);
//    }

}
