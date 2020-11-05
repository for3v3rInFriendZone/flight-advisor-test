package com.flight.advisor.exception.user;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class UsernameAlreadyTakenException extends GenericException {

    private static final String USERNAME_ALREADY_EXISTS_KEY = "username.already.exists";

    public UsernameAlreadyTakenException(String username) {
        super(USERNAME_ALREADY_EXISTS_KEY, HttpStatus.CONFLICT);

        log.error("User with username: * {} * already exists", username);
    }
}
