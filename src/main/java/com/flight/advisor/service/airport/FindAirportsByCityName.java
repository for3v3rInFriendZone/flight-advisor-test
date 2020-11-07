package com.flight.advisor.service.airport;

import com.flight.advisor.exception.airport.AirportNotFoundForCityException;
import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FindAirportsByCityName {

    private final AirportRepository airportRepository;

    public List<Airport> execute(String city) {
        final List<Airport> airports = airportRepository.findAllByCity(city);

        if (airports.isEmpty()) {
            throw new AirportNotFoundForCityException(city);
        }

        return airports;
    }
}
