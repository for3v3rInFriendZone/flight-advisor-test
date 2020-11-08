package com.flight.advisor.service.city;

import com.flight.advisor.model.City;
import com.flight.advisor.repository.CityRepository;
import com.flight.advisor.util.TestsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllCitiesPaginatedTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private GetAllCitiesPaginated getAllCitiesPaginated;

    @Test
    void getAllCities_PaginationValues_ReturnsCitiesWithinPagination() {
        // Setup
        final Integer page = 0;
        final Integer size = 2;
        final List<City> cities = TestsHelper.getCities().stream()
                .limit(size)
                .collect(Collectors.toList());

        // Mocks
        when(cityRepository.getAllPaginated(any(Integer.class), any(Integer.class)))
                .thenReturn(cities);

        // Act
        final List<City> result = getAllCitiesPaginated.execute(page, size);

        // Assert
        assertThat(result.size()).isEqualTo(size);
    }

    @Test
    void getAllCities_PaginationValues_ReturnsEmptyResponse() {
        // Setup
        final Integer page = 0;
        final Integer size = 2;

        // Mocks
        when(cityRepository.getAllPaginated(any(Integer.class), any(Integer.class)))
                .thenReturn(new ArrayList<>());

        // Act
        final List<City> result = getAllCitiesPaginated.execute(page, size);

        // Assert
        assertThat(result.isEmpty()).isTrue();
    }
}