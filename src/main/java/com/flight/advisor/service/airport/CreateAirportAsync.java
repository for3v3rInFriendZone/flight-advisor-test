package com.flight.advisor.service.airport;

import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateAirportAsync {

    private final AirportRepository airportRepository;

    @Async
    public void execute(Airport airport) {
        airportRepository.save(airport);
    }
}
