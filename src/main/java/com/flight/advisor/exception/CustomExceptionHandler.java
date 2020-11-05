package com.flight.advisor.exception;

import com.flight.advisor.util.ResourceBundleResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ExceptionResponse> handleCustomExceptions(GenericException ge) {
        final ExceptionResponse response = new ExceptionResponse(ge.getMessage(), ge.getHttpStatus().toString());

        return new ResponseEntity<>(response, ge.getHttpStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleArgumentNotValid(MethodArgumentNotValidException me) {
        final String validationMessageKey = me.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        final String localisedMessage = ResourceBundleResolver.getResourceByKey(validationMessageKey);
        final HttpStatus status = HttpStatus.BAD_REQUEST;

        final ExceptionResponse response = new ExceptionResponse(localisedMessage, status.toString());
        return new ResponseEntity<>(response, status);
    }
}
