package com.flight.advisor.exception.city;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Slf4j
public class CityNotFoundException extends GenericException {
    private static final String NOT_FOUND_KEY = "city.not.found";

    public CityNotFoundException(UUID id) {
        super(NOT_FOUND_KEY, HttpStatus.NOT_FOUND);

        log.error("City with an Id * {} * was not found", id);
    }
}
