package com.flight.advisor.exception.city;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class CityNotFoundException extends GenericException {
    private static final String NOT_FOUND_KEY = "city.not.found";

    public CityNotFoundException() {
        super(NOT_FOUND_KEY, HttpStatus.NOT_FOUND);
    }
}
