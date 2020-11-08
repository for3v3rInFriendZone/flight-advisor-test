package com.flight.advisor.dto.user;

import com.flight.advisor.util.Constants;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignInRequest {

    @NotBlank(message = "username.not.blank")
    @Size(max = Constants.USERNAME_MAX_SIZE, message = "username.size")
    private String username;

    @NotBlank(message = "password.not.blank")
    @Size(max = Constants.PASSWORD_MAX_SIZE, message = "password.size")
    private String password;
}
