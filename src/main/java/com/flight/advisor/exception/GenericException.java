package com.flight.advisor.exception;

import com.flight.advisor.util.ResourceBundleResolver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class GenericException extends RuntimeException {

    private String message;

    private HttpStatus httpStatus;

    public GenericException(String key, HttpStatus httpStatus) {
        super(ResourceBundleResolver.getResourceByKey(key));

        this.message = ResourceBundleResolver.getResourceByKey(key);
        this.httpStatus = httpStatus;

        log.error("Exception occurred with message: '{}' ", ResourceBundleResolver.getResourceByKey(key));
    }
}
