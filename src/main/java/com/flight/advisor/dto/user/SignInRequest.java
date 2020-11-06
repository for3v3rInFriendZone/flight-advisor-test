package com.flight.advisor.dto.user;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignInRequest {

    @NotBlank(message = "username.not.blank")
    @Size(message = "username.size")
    private String username;

    @NotBlank(message = "password.not.blank")
    @Size(message = "password.size")
    private String password;
}
