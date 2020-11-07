package com.flight.advisor.service.route;

import com.flight.advisor.model.Airport;
import com.flight.advisor.model.Route;
import com.flight.advisor.repository.AirportRepository;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.upload.routes.RouteUploadModel;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HashBasedHipsterDirectedGraph;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PopulateGraphAsync {

    private static final int IATA_CODE = 3;
    private static final int ICAO_CODE = 4;

    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;

    public void execute() {
        HashBasedHipsterDirectedGraph<Integer, Double> graph = HashBasedHipsterDirectedGraph.create();
        final List<Route> routes = routeRepository.findAll();

        for (Route route : routes) {
            graph = createGraph(route, graph).join();
        }

        HipsterDirectedGraph<Integer, Double> fullGraph = graph;
    }

    @Async
    public CompletableFuture<HashBasedHipsterDirectedGraph<Integer, Double>> createGraph(Route route,
                                                                      HashBasedHipsterDirectedGraph<Integer, Double> graph) {
        final Integer sourceAirportId = getSourceAirport(route).getId();
        final Integer destinationAirport = getDestinationAirport(route).getId();
        final double distance = route.getDistance().doubleValue();

        graph.add(sourceAirportId);
        graph.add(destinationAirport);
        graph.connect(sourceAirportId, destinationAirport, distance);
        graph.connect(destinationAirport, sourceAirportId, distance);

        return CompletableFuture.completedFuture(graph);
    }

    private Airport getSourceAirport(Route route) {
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

    private Airport getDestinationAirport(Route route) {
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
}
