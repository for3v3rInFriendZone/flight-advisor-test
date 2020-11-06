package com.flight.advisor.exception.user;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class BadCredentialsException extends GenericException {

    private static final String BAD_CREDENTIALS_KEY = "bad.credentials";

    public BadCredentialsException() {
        super(BAD_CREDENTIALS_KEY, HttpStatus.NOT_FOUND);

        log.error("Username or password are wrong");
    }
}
