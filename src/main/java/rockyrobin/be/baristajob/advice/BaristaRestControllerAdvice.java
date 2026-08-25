package rockyrobin.be.baristajob.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rockyrobin.be.baristajob.dto.response.ErrorResponse;
import rockyrobin.be.baristajob.exception.BaristaNotFoundException;
import rockyrobin.be.baristajob.exception.VestigingNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BaristaRestControllerAdvice {

    @ExceptionHandler(BaristaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBaristaNotFound(BaristaNotFoundException ex) {
        return new ErrorResponse(404, ex.getMessage(), LocalDateTime.now().toString());
    }

    @ExceptionHandler(VestigingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleVestigingNotFound(VestigingNotFoundException ex) {
        return new ErrorResponse(404, ex.getMessage(), LocalDateTime.now().toString());
    }
}