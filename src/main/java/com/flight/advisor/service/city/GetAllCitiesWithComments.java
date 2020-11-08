package com.flight.advisor.service.city;

import com.flight.advisor.converters.CityConverter;
import com.flight.advisor.converters.CommentConverter;
import com.flight.advisor.dto.city.CityResponse;
import com.flight.advisor.dto.comment.CommentResponse;
import com.flight.advisor.model.City;
import com.flight.advisor.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GetAllCitiesWithComments {

    private final GetAllCitiesPaginated getAllCities;
    private final CommentRepository commentRepository;

    public List<CityResponse> execute(Integer page, Integer size, Integer commentsLimit) {
        log.info("Getting all cities ** [page: {}, size: {}, commentsLimit: {}] **",
                page, size, commentsLimit);

        return getAllCities.execute(page, size).stream()
                .map(city -> createCityResponse(city, commentsLimit))
                .collect(Collectors.toList());
    }

    private CityResponse createCityResponse(City city, Integer commentsLimit) {
        final List<CommentResponse> comments = commentRepository
                .findAllByCityId(city.getId().toString(), commentsLimit)
                .stream()
                .map(CommentConverter::toCommentResponse)
                .collect(Collectors.toList());

        return CityConverter.toCityResponse(city, comments);
    }
}
