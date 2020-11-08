package com.flight.advisor.service.route;

import com.flight.advisor.dto.city.FlightRequest;
import com.flight.advisor.dto.city.FlightResponse;
import com.flight.advisor.model.Airport;
import com.flight.advisor.repository.AirportRepository;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.airport.FindAirportsByCityName;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
@Transactional
public class FindCheapestFlights {

    private final FindAirportsByCityName findAirportByCityName;
    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;

    public List<FlightResponse> execute(FlightRequest flightRequest) {
        final List<FlightResponse> response = new ArrayList<>();
        final List<Airport> sourceAirports = findAirportByCityName.execute(flightRequest.getSourceCity());
        final List<Airport> destinationAirports = findAirportByCityName.execute(flightRequest.getDestinationCity());
        final HipsterDirectedGraph<Integer, Double> graph = GraphBuilderSingleton.getInstance()
                .createDirectedGraph();

        for (Airport sourceAirport : sourceAirports) {
            for (Airport destinationAirport : destinationAirports) {
                final SearchProblem search = getSearchProblem(graph, sourceAirport.getId());
                final Iterator resultIterator = Hipster.createDijkstra(search)
                        .search(destinationAirport.getId())
                        .getGoalNodes()
                        .iterator();

                addFlightToResponse(resultIterator, response);
            }
        }

        return response.stream()
                .sorted(Comparator.comparing(FlightResponse::getTotalPrice))
                .collect(Collectors.toList());
    }

    private SearchProblem getSearchProblem(HipsterDirectedGraph<Integer, Double> graph, Integer source) {
        return GraphSearchProblem
                .startingFrom(source)
                .in(graph)
                .takeCostsFromEdges()
                .build();
    }

    private void addFlightToResponse(Iterator nodesIterator, List<FlightResponse> response) {
        while (nodesIterator.hasNext()) {
            final Node goalNode = (Node) nodesIterator.next();

            final List<Integer> airportsId = (List<Integer>) Algorithm.recoverStatePath(goalNode);
            final String cityRoutes = getCityRoutes(airportsId);

            final BigDecimal totalPrice = BigDecimal.valueOf((Double) ((WeightedNode) goalNode).getCost())
                    .setScale(2, RoundingMode.HALF_UP);
            if (BigDecimal.ONE.compareTo(totalPrice) > 0 || airportsId.size() > 7) {
                return;
            }

            final FlightResponse flightResponse = FlightResponse.builder()
                    .routes(cityRoutes)
                    .totalPrice(totalPrice)
                    .totalDistance(getTotalDistance(airportsId))
                    .build();

            response.add(flightResponse);
        }
    }

    private BigDecimal getTotalDistance(List<Integer> airportsId) {
        final ListIterator<Integer> iterator = airportsId.listIterator();
        double distance = 0;

        while (iterator.hasNext()) {
            final Integer currentId = iterator.next();
            final Integer nextId = iterator.nextIndex();
            if (iterator.nextIndex() == airportsId.size()) {
                break;
            }

            final Integer nextAirportId = airportsId.get(nextId);
            final Double resultDistance = routeRepository.getRouteDistance(currentId, nextAirportId).get(0); // Distance is always the same!

            distance += resultDistance;
        }

        return BigDecimal.valueOf(distance).setScale(2, RoundingMode.HALF_UP);
    }

    private String getCityRoutes(List<Integer> airportsId) {
        return airportsId.stream()
                .map(airportRepository::getOne)
                .map(airport -> String.format("%s [%s]", airport.getCity(), airport.getName()))
                .collect(joining(" --> "));
    }
}
