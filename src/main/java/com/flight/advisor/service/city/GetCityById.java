package com.flight.advisor.service.city;

import com.flight.advisor.exception.city.CityNotFoundException;
import com.flight.advisor.model.City;
import com.flight.advisor.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GetCityById {

    private final CityRepository cityRepository;

    public City execute(UUID id) {
        log.info("Trying to get city by id: {}", id);

        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }
}
