package com.flight.advisor.dto.city;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CreateCityRequest {

    @NotBlank(message = "city.name.blank")
    @Size(max = 100, message = "city.name.size")
    private String name;

    @NotBlank(message = "city.country.blank")
    @Size(max = 100, message = "city.country.size")
    private String country;

    @NotBlank(message = "city.description.blank")
    @Size(max = 1000, message = "city.description.size")
    private String description;
}
