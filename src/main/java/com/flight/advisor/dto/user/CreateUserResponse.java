package com.flight.advisor.dto.user;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateUserResponse {

    private UUID id;
}
