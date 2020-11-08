package com.flight.advisor.service.route;

import com.flight.advisor.model.Route;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import com.flight.advisor.util.GraphBuilderSingleton;
import es.usc.citius.hipster.graph.GraphBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateRouteAsync {

    private final RouteRepository routeRepository;
    private final RouteDistance routeDistance;

    @Async
    public void execute(RouteUploadModel uploadModel) {
        final BigDecimal distance = routeDistance.execute(uploadModel);
        if (distance == null) {
            //Corrupted data, don't save route
            return;
        }

        populateGraph(
                uploadModel.getSourceAirportId(),
                uploadModel.getDestinationAirportId(),
                Double.parseDouble(uploadModel.getPrice())
        );

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

    private synchronized void populateGraph(String sourceAirportId, String destinationAirportId, double price) {
        final Integer source = Integer.parseInt(sourceAirportId);
        final Integer destination = Integer.parseInt(destinationAirportId);
        final GraphBuilder<Integer, Double> graphBuilder = GraphBuilderSingleton.getInstance();

        graphBuilder.connect(source).to(destination).withEdge(price);

        log.info("Route {} --> {} with edge: {}", source, destination, price);
    }
}
