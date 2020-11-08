package com.flight.advisor.service.util;

import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import lombok.experimental.UtilityClass;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class TestsHelper {

    public City getCity() {
        return City.builder()
                .name("Novi Sad")
                .country("Serbia")
                .description("Some nice description")
                .build();
    }

    public List<City> getCities() {
        final City city1 = City.builder()
                .name("Novi Sad")
                .country("Serbia")
                .description("Some nice description")
                .build();
        city1.setId(UUID.randomUUID());

        final City city2 = City.builder()
                .name("Novgorod")
                .country("Russia")
                .description("Some other description 2")
                .build();

        city2.setId(UUID.randomUUID());

        final City city3 = City.builder()
                .name("Belgrade")
                .country("Serbia")
                .description("Some other description 2")
                .build();

        city3.setId(UUID.randomUUID());

        return List.of(city1, city2, city3);
    }

    public List<Comment> getComments() {
        final Comment comment1 = Comment.builder()
                .text("Some comment 1")
                .city(getCities().get(0))
                .createdDate(ZonedDateTime.now())
                .build();

        final Comment comment2 = Comment.builder()
                .text("Some comment 2")
                .city(getCities().get(0))
                .createdDate(ZonedDateTime.now())
                .build();

        final Comment comment3 = Comment.builder()
                .text("Some comment 3")
                .city(getCities().get(0))
                .createdDate(ZonedDateTime.now())
                .build();

        return List.of(comment1, comment2, comment3);
    }
}
