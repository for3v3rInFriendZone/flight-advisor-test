package com.flight.advisor.repository;

import com.flight.advisor.model.City;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends BaseRepository<City> {

    @Query(value = "SELECT * FROM city WHERE LOWER(name) LIKE %:name%", nativeQuery = true)
    List<City> findAllByName(String name);
}
