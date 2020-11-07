package com.flight.advisor.exception.airport;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class AirportNotFoundForCityException extends GenericException {
    private static final String AIRPORT_NOT_FOUND_FOR_CITY_KEY = "airport.not.found.for.city";

    public AirportNotFoundForCityException(String city) {
        super(AIRPORT_NOT_FOUND_FOR_CITY_KEY, HttpStatus.NOT_FOUND);

        log.error("City: {} does not contain any airports", city);
    }
}
