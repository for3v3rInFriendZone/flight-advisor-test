package com.flight.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route extends BaseEntity {

    private String airlineCode;

    private String airlineId;

    private String sourceAirportCode;

    private String sourceAirportId;

    private String destinationAirportCode;

    private String destinationAirportId;

    private String codeshare;

    private Integer stops;

    private String equipment;

    private BigDecimal price;

    private BigDecimal distance;
}
