package com.flight.advisor.converters;

import com.flight.advisor.dto.city.CreateCityRequest;
import com.flight.advisor.dto.city.CreateCityResponse;
import com.flight.advisor.model.City;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class CityConverter {

    public CreateCityResponse toCreateCityResponse(final UUID cityId, String link) {
        final String fullLink = String.format(link, cityId.toString());

        return CreateCityResponse.builder()
                .id(cityId)
                .link(fullLink)
                .build();
    }

    public City toCityFromCreateRequest(final CreateCityRequest createCityRequest) {
        return City.builder()
                .name(createCityRequest.getName())
                .country(createCityRequest.getCountry())
                .description(createCityRequest.getDescription())
                .build();
    }
}
