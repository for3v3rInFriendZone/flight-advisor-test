package com.flight.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

    @Id
    private Integer id;

    private String name;

    private String city;

    private String country;

    private String iata;

    private String icao;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Integer altitude;

    private String timezone;

    private String dst;

    private String databaseTimezone;

    private String type;

    private String source;
}
