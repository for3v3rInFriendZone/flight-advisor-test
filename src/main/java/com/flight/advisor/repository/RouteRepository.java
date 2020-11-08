package com.flight.advisor.repository;

import com.flight.advisor.model.Route;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends BaseRepository<Route> {

    @Query(value = "SELECT distance from route " +
            "where source_airport_id = :source and destination_airport_id = :destination", nativeQuery = true)
    List<Double> getRouteDistance(Integer source, Integer destination);
}
