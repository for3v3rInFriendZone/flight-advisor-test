package com.flight.advisor.service.graph;

import com.flight.advisor.model.Route;
import com.flight.advisor.util.GraphBuilderSingleton;
import es.usc.citius.hipster.graph.GraphBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PopulateGraph {

    @Async
    public void execute(Route route) {
        final Integer source = Integer.parseInt(route.getSourceAirportId());
        final Integer destination = Integer.parseInt(route.getDestinationAirportId());
        final double price = route.getPrice().doubleValue();
        final GraphBuilder<Integer, Double> graphBuilder = GraphBuilderSingleton.getInstance();

        connect(graphBuilder, source, destination, price);
    }

    private synchronized void connect(GraphBuilder<Integer, Double> graphBuilder, Integer source, Integer destination, double price) {
        graphBuilder.connect(source).to(destination).withEdge(price);

        log.info("Route {} --> {} with edge: {}", source, destination, price);
    }
}
