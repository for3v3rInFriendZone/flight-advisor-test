package com.flight.advisor.dto.user;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CreateUserRequest {

    @NotBlank(message = "user.firstname.blank")
    @Size(max = 100, message = "user.firstname.size")
    private String firstName;

    @NotBlank(message = "user.lastName.blank")
    @Size(max = 100, message = "user.lastName.size")
    private String lastName;

    @NotBlank(message = "user.username.blank")
    @Size(max = 100, message = "user.username.size")
    private String username;

    @NotBlank(message = "user.password.blank")
    @Size(max = 100, message = "user.password.size")
    private String password;
}
