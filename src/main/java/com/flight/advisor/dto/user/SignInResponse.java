package com.flight.advisor.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    private String token;
}
