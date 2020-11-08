package com.flight.advisor.service.city;

import com.flight.advisor.model.City;
import com.flight.advisor.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GetAllCities {

    private final CityRepository cityRepository;

    public List<City> getAllPaginated(Integer page, Integer size) {
        final Integer limit = size;
        final Integer offset = page * size;

        return cityRepository.getAllPaginated(limit, offset);
    }
}
