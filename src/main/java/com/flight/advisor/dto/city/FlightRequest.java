package com.flight.advisor.dto.city;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class FlightRequest {

    @NotBlank(message = "city.name.blank")
    @Size(max = 100, message = "city.name.size")
    private String sourceCity;

    @NotBlank(message = "city.name.blank")
    @Size(max = 100, message = "city.name.size")
    private String destinationCity;
}
