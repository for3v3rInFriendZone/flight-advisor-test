package com.flight.advisor.service.route;

import com.flight.advisor.dto.city.FlightRequest;
import com.flight.advisor.dto.city.FlightResponse;
import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.airport.FindAirportByCityName;
import com.flight.advisor.util.GraphBuilderSingleton;
import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.Node;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.SearchProblem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
@Transactional
public class FindCheapestFlights {

    private final FindAirportByCityName findAirportByCityName;
    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;

    public List<FlightResponse> execute(FlightRequest flightRequest) {
        final Integer sourceAirportId = findAirportByCityName.execute(flightRequest.getSourceCity()).getId();
        final Integer destinationAirportId = findAirportByCityName.execute(flightRequest.getDestinationCity()).getId();

        HipsterDirectedGraph<Integer, Double> graph = GraphBuilderSingleton.getInstance()
                .createDirectedGraph();

        final SearchProblem search = getSearchProblem(graph, sourceAirportId);
        final Iterator resultIterator = Hipster.createDijkstra(search).search(destinationAirportId)
                .getGoalNodes()
                .iterator();

        return toResponse(resultIterator);
    }

    private SearchProblem getSearchProblem(HipsterDirectedGraph<Integer, Double> graph, Integer source) {
        return GraphSearchProblem
                .startingFrom(source)
                .in(graph)
                .takeCostsFromEdges()
                .build();
    }

    private List<FlightResponse> toResponse(Iterator nodesIterator) {
        final List<FlightResponse> response = new ArrayList<>();

        while (nodesIterator.hasNext()) {
            final Node goalNode = (Node) nodesIterator.next();
            final List<Integer> citiesId = (List<Integer>) Algorithm.recoverStatePath(goalNode);

            final String cityRoutes = citiesId.stream()
                    .map(airportRepository::getOne)
                    .collect(Collectors.toMap(Airport::getCity, Airport::getName))
                    .entrySet().stream()
                    .map(entry -> String.format("%s(%s)", entry.getKey(), entry.getValue()))
                    .collect(joining(" --> "));

            final BigDecimal totalPrice = BigDecimal.valueOf((Double) ((WeightedNode) goalNode).getCost());

            final FlightResponse flightResponse = FlightResponse.builder()
                    .routes(cityRoutes)
                    .totalPrice(totalPrice)
                    .build();

            response.add(flightResponse);
        }

        return response.stream()
                .sorted(Comparator.comparing(FlightResponse::getTotalPrice))
                .collect(Collectors.toList());
    }


}
