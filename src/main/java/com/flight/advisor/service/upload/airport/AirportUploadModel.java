package com.flight.advisor.service.upload.airport;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AirportUploadModel {

    private String id;

    private String name;

    private String city;

    private String country;

    private String iata;

    private String icao;

    private String latitude;

    private String longitude;

    private String altitude;

    private String timezone;

    private String dst;

    private String databaseTimezone;

    private String type;

    private String source;
}
