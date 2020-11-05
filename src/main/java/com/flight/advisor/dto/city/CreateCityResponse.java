package com.flight.advisor.dto.city;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CreateCityResponse {

    private UUID id;
}
