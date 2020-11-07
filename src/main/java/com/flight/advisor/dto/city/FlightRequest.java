package com.flight.advisor.dto.city;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class FlightRequest {

    @NotBlank
    @Size(max = 200)
    private String sourceCity;

    @NotBlank
    @Size(max = 200)
    private String destinationCity;
}
