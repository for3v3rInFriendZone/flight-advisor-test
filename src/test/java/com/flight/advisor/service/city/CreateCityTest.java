package com.flight.advisor.service.city;

import com.flight.advisor.model.City;
import com.flight.advisor.repository.CityRepository;
import com.flight.advisor.service.util.TestsHelper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCityTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CreateCity createCity;

    @Test
    void saveCity_NewCityReturns_SavedCity() {
        // Setup
        final City newCity = TestsHelper.getCity();

        // Mocks
        when(cityRepository.save(any(City.class))).thenReturn(newCity);

        // Act
        final City result = createCity.execute(newCity);

        // Assert
        assertThat(result.getName()).isNotBlank();
        assertThat(result.getCountry()).isNotBlank();
        assertThat(result.getDescription()).isNotBlank();
    }
}