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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCitiesByNameTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private FindCitiesByName findCitiesByName;

    @Test
    void findAllByName_NameAndPaging_ReturnsCitiesWithProvidedName() {
        // Setup
        final List<City> cities = TestsHelper.getCities();
        final Integer page = 0;
        final Integer size = 10;
        final String nameQuery = "Nov";

        // Mocks
        when(cityRepository.findAllByName(any(String.class), any(Integer.class), any(Integer.class)))
                .thenReturn(cities);

        // Act
        final List<City> result = findCitiesByName.execute(nameQuery, page, size);

        // Assert
        assertThat(result.size()).isLessThanOrEqualTo(size);
        assertThat(result.get(0).getName()).contains(nameQuery);
        assertThat(result.get(1).getName()).contains(nameQuery);
    }

    @Test
    void findAllByName_NameAndPaging_ReturnsEmptyResponse() {
        // Setup
        final Integer page = 0;
        final Integer size = 10;
        final String nameQuery = "Belg";


        // Mocks
        when(cityRepository.findAllByName(any(String.class), any(Integer.class), any(Integer.class)))
                .thenReturn(new ArrayList<>());

        // Act
        final List<City> result = findCitiesByName.execute(nameQuery, page, size);

        // Assert
        assertThat(result.isEmpty()).isTrue();
    }
}