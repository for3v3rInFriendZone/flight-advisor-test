package com.flight.advisor.dto.city;

import com.flight.advisor.util.Constants;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CreateCityRequest {

    @NotBlank(message = "city.name.blank")
    @Size(max = Constants.CITY_NAME_MAX_SIZE, message = "city.name.size")
    private String name;

    @NotBlank(message = "city.country.blank")
    @Size(max = Constants.COUNTRY_NAME_MAX_SIZE, message = "city.country.size")
    private String country;

    @NotBlank(message = "city.description.blank")
    @Size(max = Constants.CITY_DESCRIPTION_MAX_SIZE, message = "city.description.size")
    private String description;
}
