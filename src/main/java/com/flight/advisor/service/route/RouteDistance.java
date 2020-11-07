package com.flight.advisor.service.route;

import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import com.flight.advisor.util.CalculateDistance;
import com.flight.advisor.util.GraphBuilderSingleton;
import es.usc.citius.hipster.graph.GraphBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RouteDistance {

    private static final int IATA_CODE = 3;
    private static final int ICAO_CODE = 4;

    private final AirportRepository airportRepository;

    public BigDecimal execute(RouteUploadModel route) {
        final Airport sourceAirport = getSourceAirport(route);
        final Airport destinationAirport = getDestinationAirport(route);

        if (sourceAirport == null || destinationAirport == null) {
            return null;
        }

        final double lat1 = sourceAirport.getLatitude().doubleValue();
        final double lat2 = destinationAirport.getLatitude().doubleValue();

        final double lon1 = sourceAirport.getLongitude().doubleValue();
        final double lon2 = destinationAirport.getLongitude().doubleValue();

        final double alt1 = fromFeetToMeters(sourceAirport.getAltitude().doubleValue());
        final double alt2 = fromFeetToMeters(destinationAirport.getAltitude().doubleValue());

        final BigDecimal distance = CalculateDistance.calculate(lat1, lat2, lon1, lon2, alt1, alt2);

        populateGraph(sourceAirport.getId(), destinationAirport.getId(), Double.parseDouble(route.getPrice()));
        return distance;
    }

    private static synchronized void populateGraph(Integer sourceAirportId, Integer destinationAirportId, double price) {
        final GraphBuilder<Integer, Double> graphBuilder = GraphBuilderSingleton.getInstance();

        graphBuilder.connect(sourceAirportId).to(destinationAirportId).withEdge(price);

        log.info("Route {} --> {} with edge: {}", sourceAirportId, destinationAirportId, price);
    }

    private Airport getSourceAirport(RouteUploadModel route) {
        final String airportIdString = route.getSourceAirportId();
        final String airportCode = route.getSourceAirportCode();

        if (isInteger(airportIdString)) {
            final Integer airportId = Integer.parseInt(airportIdString);

            return airportRepository.findById(airportId)
                    .orElse(getAirportByCode(airportCode));
        } else {
            return getAirportByCode(airportCode);
        }
    }

    private Airport getDestinationAirport(RouteUploadModel route) {
        final String airportIdString = route.getDestinationAirportId();
        final String airportCode = route.getDestinationAirportCode();

        if (isInteger(airportIdString)) {
            final Integer airportId = Integer.parseInt(airportIdString);

            return airportRepository.findById(airportId)
                    .orElse(getAirportByCode(airportCode));
        } else {
            return getAirportByCode(airportCode);
        }
    }

    private Airport getAirportByCode(String code) {
        if (code.length() == IATA_CODE) {
            return airportRepository.findByIata(code);
        } else if (code.length() == ICAO_CODE) {
            return airportRepository.findByIcao(code);
        }

        return null;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private double fromFeetToMeters(double feet) {
        final double oneFeetMeters = 3.280839895;

        return feet / oneFeetMeters;
    }
}
