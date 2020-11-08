package com.flight.advisor.service.city;

import com.flight.advisor.exception.city.CityNotFoundException;
import com.flight.advisor.model.City;
import com.flight.advisor.repository.CityRepository;
import com.flight.advisor.service.util.TestsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCityByIdTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private GetCityById getCityById;

    @Test
    void getCityById_IdValue_ReturnsCityWithProvidedId() {
        // Setup
        final UUID id = UUID.randomUUID();
        final City city = TestsHelper.getCity();
        city.setId(id);

        // Mocks
        when(cityRepository.findById(any(UUID.class))).thenReturn(Optional.of(city));

        // Act
        final City result = getCityById.execute(id);

        // Assert
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getName()).isNotBlank();
    }

    @Test
    void getCityById_IdValue_ThrowsCityNotFoundException() {
        // Setup
        final UUID id = UUID.randomUUID();
        final City city = TestsHelper.getCity();
        city.setId(id);

        // Mocks
        when(cityRepository.findById(any(UUID.class))).thenThrow(new CityNotFoundException(UUID.randomUUID()));

        // Act
        assertThrows(CityNotFoundException.class, () -> getCityById.execute(UUID.randomUUID()));
    }
}