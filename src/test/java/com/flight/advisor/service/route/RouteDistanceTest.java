package com.flight.advisor.service.route;

import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import com.flight.advisor.util.TestsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteDistanceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private RouteDistance routeDistance;

    @Test
    void routeDistance_RouteModel_ReturnsDistanceBetweenTwoPoints() {
        // Setup
        final String airportId1 = "1231";
        final String airportId2 = "3321";
        final RouteUploadModel routeModel = TestsHelper.getRouteModel(airportId1, airportId2);
        final List<Airport> airports = TestsHelper.getAirports();
        final BigDecimal expectedResult = BigDecimal.valueOf(72.07);

        // Mocks
        when(airportRepository.findById(Integer.parseInt(airportId1))).thenReturn(Optional.of(airports.get(0)));
        when(airportRepository.findById(Integer.parseInt(airportId2))).thenReturn(Optional.of(airports.get(1)));

        // Act
        final BigDecimal result = routeDistance.execute(routeModel);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }
}