package com.flight.advisor.service.route;

import com.flight.advisor.model.Route;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveRoute {

    private final RouteRepository routeRepository;
    private final RouteDistance routeDistance;

    @Async
    public void execute(RouteUploadModel uploadModel) {
        final BigDecimal distance = routeDistance.execute(uploadModel);
        if (distance == null) {
            //Corrupted data, don't save route
            return;
        }

        final Route route = Route.builder()
                .airlineCode(uploadModel.getAirlineCode())
                .airlineId(uploadModel.getAirlineId())
                .sourceAirportCode(uploadModel.getSourceAirportCode())
                .sourceAirportId(uploadModel.getSourceAirportId())
                .destinationAirportCode(uploadModel.getDestinationAirportCode())
                .destinationAirportId(uploadModel.getDestinationAirportId())
                .codeshare(uploadModel.getCodeshare())
                .stops(Integer.parseInt(uploadModel.getStops()))
                .equipment(uploadModel.getEquipment())
                .price(new BigDecimal(uploadModel.getPrice()))
                .distance(distance)
                .build();

        routeRepository.save(route);
    }
}
