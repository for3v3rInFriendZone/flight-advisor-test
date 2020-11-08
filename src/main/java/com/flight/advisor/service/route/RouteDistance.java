package com.flight.advisor.service.route;

import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import com.flight.advisor.util.CalculateDistance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteDistance {

    private final AirportRepository airportRepository;

    public BigDecimal execute(RouteUploadModel route) {
        final Airport sourceAirport = getAirport(route.getSourceAirportId());
        final Airport destinationAirport = getAirport(route.getDestinationAirportId());

        if (sourceAirport == null || destinationAirport == null) {
            return null;
        }

        final double lat1 = sourceAirport.getLatitude().doubleValue();
        final double lat2 = destinationAirport.getLatitude().doubleValue();

        final double lon1 = sourceAirport.getLongitude().doubleValue();
        final double lon2 = destinationAirport.getLongitude().doubleValue();

        final double alt1 = fromFeetToMeters(sourceAirport.getAltitude().doubleValue());
        final double alt2 = fromFeetToMeters(destinationAirport.getAltitude().doubleValue());

        return CalculateDistance.calculate(lat1, lat2, lon1, lon2, alt1, alt2);
    }

    private Airport getAirport(String id) {
        final Integer airportId = Integer.parseInt(id);

        return airportRepository.findById(airportId)
                .orElse(null);
    }

    private double fromFeetToMeters(double feet) {
        final double oneFeetMeters = 3.280839895;

        return feet / oneFeetMeters;
    }
}
