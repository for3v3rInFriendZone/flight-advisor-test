package com.flight.advisor.dto.comment;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateCommentResponse {

    private UUID id;
}
