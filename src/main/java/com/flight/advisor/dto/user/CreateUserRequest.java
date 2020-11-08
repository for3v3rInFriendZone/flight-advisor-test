package com.flight.advisor.dto.user;

import com.flight.advisor.util.Constants;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CreateUserRequest {

    @NotBlank(message = "user.firstname.blank")
    @Size(max = Constants.FIRSTNAME_MAX_SIZE, message = "user.firstname.size")
    private String firstName;

    @NotBlank(message = "user.lastName.blank")
    @Size(max = Constants.LASTNAME_MAX_SIZE, message = "user.lastName.size")
    private String lastName;

    @NotBlank(message = "user.username.blank")
    @Size(max = Constants.USERNAME_MAX_SIZE, message = "user.username.size")
    private String username;

    @NotBlank(message = "user.password.blank")
    @Size(max = Constants.PASSWORD_MAX_SIZE, message = "user.password.size")
    private String password;
}
