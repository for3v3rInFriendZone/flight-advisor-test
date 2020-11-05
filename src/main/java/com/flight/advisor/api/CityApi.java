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
import com.flight.advisor.service.city.GetAllCitiesWithComments;
import com.flight.advisor.service.comment.CreateComment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class CityApi {

    private final CreateCity createCity;
    private final CreateComment createComment;
    private final GetAllCitiesWithComments getAllCitiesWithComments;

    @GetMapping
    public List<CityResponse> findAllCities(@RequestParam(required = false) Integer commentsLimit) {
        return getAllCitiesWithComments.execute(commentsLimit);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCityResponse createCity(@RequestBody @Valid CreateCityRequest createCityRequest) {
        final City createdCity = createCity.execute(CityConverter.toCityFromCreateRequest(createCityRequest));

        return CityConverter.toCreateCityResponse(createdCity.getId());
    }

//    @GetMapping
//    public List<CityResponse> findAllCitiesByName(@RequestParam String name) {
//        return null;
//    }

    @PostMapping("/{cityId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponse createComment(@PathVariable UUID cityId, @RequestBody @Valid CreateCommentRequest createCommentRequest) {
        final Comment newComment = createComment.execute(CommentConverter.toCommentFromCreateComment(createCommentRequest), cityId);

        return CommentConverter.toCreateCommentResponse(newComment);
    }
}
