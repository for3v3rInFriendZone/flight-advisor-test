package com.flight.advisor.dto.city;


import com.flight.advisor.util.Constants;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class FlightRequest {

    @NotBlank(message = "city.name.blank")
    @Size(max = Constants.CITY_NAME_MAX_SIZE, message = "city.name.size")
    private String sourceCity;

    @NotBlank(message = "city.name.blank")
    @Size(max = Constants.CITY_NAME_MAX_SIZE, message = "city.name.size")
    private String destinationCity;
}
