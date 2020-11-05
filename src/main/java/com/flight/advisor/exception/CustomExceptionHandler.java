package com.flight.advisor.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ExceptionResponse> handleCustomExceptions(GenericException ge) {
        final ExceptionResponse response = new ExceptionResponse(ge.getMessage(), ge.getHttpStatus().toString());

        return new ResponseEntity<>(response, ge.getHttpStatus());
    }
}
