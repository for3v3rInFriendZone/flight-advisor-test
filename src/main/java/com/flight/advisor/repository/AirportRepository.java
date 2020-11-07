package com.flight.advisor.repository;

import com.flight.advisor.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Airport findByIata(String iata);

    Airport findByIcao(String icao);

    List<Airport> findAllByCity(String city);
}
