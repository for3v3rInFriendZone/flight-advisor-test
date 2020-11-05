package com.flight.advisor.api;

import com.flight.advisor.converters.CityConverter;
import com.flight.advisor.converters.CommentConverter;
import com.flight.advisor.dto.city.CityResponse;
import com.flight.advisor.dto.city.CreateCityRequest;
import com.flight.advisor.dto.city.CreateCityResponse;
import com.flight.advisor.dto.comment.CreateCommentRequest;
import com.flight.advisor.dto.comment.CreateCommentResponse;
import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import com.flight.advisor.service.city.CreateCity;
import com.flight.advisor.service.city.GetAllCities;
import com.flight.advisor.service.city.GetCityById;
import com.flight.advisor.service.comment.CreateComment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityApi {

    private final CreateCity createCity;
    private final GetCityById getCityById;
    private final GetAllCities getAllCities;
    private final CreateComment createComment;

    @GetMapping
    public List<CityResponse> findAllCities() {
        return getAllCities.execute().stream()
                .map(CityConverter::toCityResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCityResponse createCity(@RequestBody @Valid CreateCityRequest createCityRequest) {
        final City createdCity = createCity.execute(CityConverter.toCityFromCreateRequest(createCityRequest));

        return CityConverter.toCreateCityResponse(createdCity.getId());
    }

    @GetMapping("/{id}")
    public CityResponse getCityById(@PathVariable UUID id) {
        final City city = getCityById.execute(id);

        return CityConverter.toCityResponse(city);
    }

    @PostMapping("/{cityId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponse createComment(@PathVariable UUID cityId, @RequestBody @Valid CreateCommentRequest createCommentRequest) {
        final Comment newComment = createComment.execute(CommentConverter.toCommentFromCreateComment(createCommentRequest), cityId);

        return CommentConverter.toCreateCommentResponse(newComment);
    }
}
