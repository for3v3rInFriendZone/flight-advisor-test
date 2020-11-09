package com.flight.advisor.api;

import com.flight.advisor.model.Route;
import com.flight.advisor.repository.RouteRepository;
import com.flight.advisor.service.graph.PopulateGraph;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
@RequiredArgsConstructor
@Slf4j
public class GraphApi {

    private static final String POPULATE_API = "/populate";

    private final RouteRepository routeRepository;
    private final PopulateGraph populateGraph;

    @PostMapping(POPULATE_API)
    @PreAuthorize("@userTypePermission.hasAny('ADMIN')")
    public void populateGraph() {
        final List<Route> routes = routeRepository.findAll();

        for(Route route : routes) {
            populateGraph.execute(route);
        }
    }
}
