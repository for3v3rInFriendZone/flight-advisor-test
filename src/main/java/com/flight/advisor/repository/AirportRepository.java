package com.flight.advisor.repository;

import com.flight.advisor.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Airport findByIata(String iata);

    Airport findByIcao(String icao);

    Optional<Airport> findByCity(String city);
}
