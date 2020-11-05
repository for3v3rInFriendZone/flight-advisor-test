package com.flight.advisor.dto.city;

import com.flight.advisor.model.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class CityResponse {

    private UUID id;

    private String name;

    private String country;

    private List<Comment> comments;
}
