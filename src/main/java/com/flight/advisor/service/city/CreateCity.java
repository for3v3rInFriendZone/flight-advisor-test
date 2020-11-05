package com.flight.advisor.service.city;

import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import com.flight.advisor.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateCity {

    private final CityRepository cityRepository;

    public City execute(City city) {
        log.info("Trying to create city: {}", city.getName());

        return cityRepository.save(city);
    }
}
