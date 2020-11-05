package com.flight.advisor.exception.city;

import com.flight.advisor.exception.GenericException;
import org.springframework.http.HttpStatus;

public class CreateCityException extends GenericException {
    
    public CreateCityException() {
        super("city.name.blank", HttpStatus.BAD_REQUEST);
    }
}
