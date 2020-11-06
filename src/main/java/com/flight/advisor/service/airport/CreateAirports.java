package com.flight.advisor.service.airport;

import com.flight.advisor.model.Airport;
import com.flight.advisor.model.City;
import com.flight.advisor.service.city.CreateCityAsync;
import com.flight.advisor.service.upload.airport.AirportUploadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateAirports {

    private final CreateCityAsync createCityAsync;
    private final CreateAirportAsync createAirportAsync;

    public void execute(List<AirportUploadModel> airports) {
        final List<City> citiesToSave = airports.stream()
                .map(this::toCity)
                .collect(Collectors.toList());

        final List<Airport> airportsToSave = airports.stream()
                .map(this::toAirport)
                .collect(Collectors.toList());

        for (City city : citiesToSave) {
            createCityAsync.execute(city);
        }

        for (Airport airport : airportsToSave) {
            createAirportAsync.execute(airport);
        }

        log.info("Airports upload finished successfully.");
    }

    private City toCity(AirportUploadModel airportUploadModel) {
        return City.builder()
                .name(airportUploadModel.getCity())
                .country(airportUploadModel.getCountry())
                .description(String.format("%s is the coolest city ever!", airportUploadModel.getCity()))
                .build();
    }

    private Airport toAirport(AirportUploadModel airportUploadModel) {
        return Airport.builder()
                .id(Integer.parseInt(airportUploadModel.getId()))
                .name(airportUploadModel.getName())
                .city(airportUploadModel.getCity())
                .country(airportUploadModel.getCountry())
                .iata(airportUploadModel.getIata())
                .icao(airportUploadModel.getIcao())
                .icao(airportUploadModel.getIcao())
                .latitude(new BigDecimal(airportUploadModel.getLatitude()))
                .longitude(new BigDecimal(airportUploadModel.getLongitude()))
                .altitude(Integer.valueOf(airportUploadModel.getAltitude()))
                .timezone(airportUploadModel.getTimezone())
                .dst(airportUploadModel.getDst())
                .databaseTimezone(airportUploadModel.getDatabaseTimezone())
                .type(airportUploadModel.getType())
                .source(airportUploadModel.getSource())
                .build();
    }
}
