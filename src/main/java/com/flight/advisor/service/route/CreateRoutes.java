package com.flight.advisor.service.route;

import com.flight.advisor.model.Route;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateRoutes {

    private final RouteRepository routeRepository;

    public void execute(List<RouteUploadModel> routes) {
        final List<Route> routesToSave = routes.stream()
                .map(this::toRoute)
                .collect(Collectors.toList());

        routeRepository.saveAll(routesToSave);
    }

    private Route toRoute(RouteUploadModel routeUploadModel) {
        return Route.builder()
                .airlineCode(routeUploadModel.getAirlineCode())
                .airlineId(routeUploadModel.getAirlineId())
                .sourceAirportCode(routeUploadModel.getSourceAirportCode())
                .sourceAirportId(routeUploadModel.getSourceAirportId())
                .destinationAirportCode(routeUploadModel.getDestinationAirportCode())
                .destinationAirportId(routeUploadModel.getDestinationAirportId())
                .codeshare(routeUploadModel.getCodeshare())
                .stops(Integer.parseInt(routeUploadModel.getStops()))
                .equipment(routeUploadModel.getEquipment())
                .price(new BigDecimal(routeUploadModel.getPrice()))
                .build();
    }
}
