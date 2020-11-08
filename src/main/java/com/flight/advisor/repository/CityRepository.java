package com.flight.advisor.repository;

import com.flight.advisor.model.City;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends BaseRepository<City> {

    @Query(value = "SELECT * FROM city " +
            "WHERE LOWER(name) LIKE %:name% " +
            "ORDER BY country, name " +
            "LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<City> findAllByName(String name, Integer limit, Integer offset);

    @Query(value = "SELECT * FROM city " +
            "ORDER BY country, name " +
            "LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<City> getAllPaginated(Integer limit, Integer offset);
}
