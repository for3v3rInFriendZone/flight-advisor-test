package com.flight.advisor.exception.user;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class UserNotFoundException extends GenericException {

    private static final String USER_NOT_FOUND_KEY = "user.not.found";

    public UserNotFoundException(String username) {
        super(USER_NOT_FOUND_KEY, HttpStatus.NOT_FOUND);

        log.error("User with an username: {} not found", username);
    }
}
