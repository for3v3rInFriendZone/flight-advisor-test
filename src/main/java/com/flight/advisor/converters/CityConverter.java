package com.flight.advisor.converters;

import com.flight.advisor.dto.city.CityResponse;
import com.flight.advisor.dto.city.CreateCityRequest;
import com.flight.advisor.dto.city.CreateCityResponse;
import com.flight.advisor.dto.comment.CommentResponse;
import com.flight.advisor.model.City;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class CityConverter {

    public CreateCityResponse toCreateCityResponse(final UUID cityId) {
        return CreateCityResponse.builder()
                .id(cityId)
                .build();
    }

    public City toCityFromCreateRequest(final CreateCityRequest createCityRequest) {
        return City.builder()
                .name(createCityRequest.getName())
                .country(createCityRequest.getCountry())
                .description(createCityRequest.getDescription())
                .build();
    }

    public CityResponse toCityResponse(City city, List<CommentResponse> comments) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .country(city.getCountry())
                .comments(comments)
                .build();
    }
}
