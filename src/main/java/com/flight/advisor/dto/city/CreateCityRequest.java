package com.flight.advisor.dto.city;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateCityRequest {

    @NotBlank(message = "city.name.blank")
    private String name;

    @NotBlank(message = "city.country.blank")
    private String country;

    @NotBlank(message = "city.description.blank")
    private String description;
}
