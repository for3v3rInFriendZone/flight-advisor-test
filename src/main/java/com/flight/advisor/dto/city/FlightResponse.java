package com.flight.advisor.dto.city;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class FlightResponse {

    private BigDecimal totalPrice;

    private BigDecimal totalDistance;

    private String routes;
}
