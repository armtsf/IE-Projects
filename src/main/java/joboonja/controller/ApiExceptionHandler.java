package joboonja.controller;

import joboonja.utils.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.InvalidObjectException;
import java.util.Date;
import java.util.NoSuchElementException;

@RestController
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ResponseMessage> handleNotFoundException(NoSuchElementException e) {
        ResponseMessage responseMessage = new ResponseMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public final ResponseEntity<ResponseMessage> handleIllegalAccessException(IllegalAccessException e) {
        ResponseMessage responseMessage = new ResponseMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidObjectException.class)
    public final ResponseEntity<ResponseMessage> handleInvalidObjectException(InvalidObjectException e) {
        ResponseMessage responseMessage = new ResponseMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ResponseMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        ResponseMessage responseMessage = new ResponseMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
