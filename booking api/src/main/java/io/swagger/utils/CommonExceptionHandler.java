package io.swagger.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created on 18.01.20.
 *
 * @author Max
 */
@ControllerAdvice
public class CommonExceptionHandler {

    // missing or invalid parameters
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    // missing body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
}
