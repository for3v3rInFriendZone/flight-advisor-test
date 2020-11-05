package com.flight.advisor.service.city;

import com.flight.advisor.model.City;
import com.flight.advisor.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FindCitiesByName {

    private final CityRepository cityRepository;

    public List<City> execute(String name) {
        return cityRepository.findAllByName(name.toLowerCase());
    }
}