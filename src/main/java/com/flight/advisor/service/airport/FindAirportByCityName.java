package com.flight.advisor.service.airport;

import com.flight.advisor.exception.airport.AirportNotFoundForCityException;
import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FindAirportByCityName {

    private final AirportRepository airportRepository;

    public Airport execute(String city) {
        return airportRepository.findByCity(city)
                .orElseThrow(() -> new AirportNotFoundForCityException(city));
    }
}
