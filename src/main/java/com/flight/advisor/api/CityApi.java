package com.flight.advisor.api;

import com.flight.advisor.converters.CityConverter;
import com.flight.advisor.converters.CommentConverter;
import com.flight.advisor.dto.city.CityResponse;
import com.flight.advisor.dto.city.CreateCityRequest;
import com.flight.advisor.dto.city.CreateCityResponse;
import com.flight.advisor.dto.city.FlightRequest;
import com.flight.advisor.dto.city.FlightResponse;
import com.flight.advisor.dto.comment.CreateCommentRequest;
import com.flight.advisor.dto.comment.CreateCommentResponse;
import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import com.flight.advisor.service.city.CreateCity;
import com.flight.advisor.service.city.FindCitiesByNameWithComments;
import com.flight.advisor.service.city.GetAllCitiesWithComments;
import com.flight.advisor.service.comment.CreateComment;
import com.flight.advisor.service.route.FindCheapestFlights;
import com.flight.advisor.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
@Slf4j
public class CityApi {

    private final CreateCity createCity;
    private final CreateComment createComment;
    private final GetAllCitiesWithComments getAllCitiesWithComments;
    private final FindCitiesByNameWithComments findCitiesByNameWithComments;
    private final FindCheapestFlights findCheapestFlights;

    @GetMapping
    @PreAuthorize("@userTypePermission.hasAny('REGULAR', 'ADMIN')")
    public List<CityResponse> getAllCities(
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = Constants.MAX_PAGE_SIZE) Integer size,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_SIZE_COMMENT) Integer commentsLimit) {
        return getAllCitiesWithComments.execute(page, size, commentsLimit);
    }

    @GetMapping("/search")
    @PreAuthorize("@userTypePermission.hasAny('REGULAR', 'ADMIN')")
    public List<CityResponse> findAllCitiesByName(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = Constants.MAX_PAGE_SIZE) Integer size,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_SIZE_COMMENT) Integer commentsLimit) {
        return findCitiesByNameWithComments.execute(name, commentsLimit, page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@userTypePermission.hasAny('ADMIN')")
    public CreateCityResponse createCity(@RequestBody @Valid CreateCityRequest createCityRequest) {
        final City createdCity = createCity.execute(CityConverter.toCityFromCreateRequest(createCityRequest));

        return CityConverter.toCreateCityResponse(createdCity.getId());
    }

    @PostMapping("/{cityId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@userTypePermission.hasAny('REGULAR', 'ADMIN')")
    public CreateCommentResponse createComment(@PathVariable UUID cityId, @RequestBody @Valid CreateCommentRequest createCommentRequest) {
        final Comment newComment = createComment.execute(CommentConverter.toCommentFromCreateComment(createCommentRequest), cityId);

        return CommentConverter.toCreateCommentResponse(newComment);
    }

    @PostMapping("/flight")
    @PreAuthorize("@userTypePermission.hasAny('REGULAR', 'ADMIN')")
    public List<FlightResponse> calculateCheapestFlight(@RequestBody @Valid FlightRequest flightRequest) {
        return findCheapestFlights.execute(flightRequest);
    }
}