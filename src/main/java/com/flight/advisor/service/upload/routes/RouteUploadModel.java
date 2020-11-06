package com.flight.advisor.service.upload.routes;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RouteUploadModel {

    private String airlineCode;

    private String airlineId;

    private String sourceAirportCode;

    private String sourceAirportId;

    private String destinationAirportCode;

    private String destinationAirportId;

    private String codeshare;

    private String stops;

    private String equipment;

    private String price;
}
