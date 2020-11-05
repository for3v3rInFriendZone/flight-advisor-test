package com.flight.advisor.api;

import com.flight.advisor.converters.UserConverter;
import com.flight.advisor.dto.user.CreateUserRequest;
import com.flight.advisor.dto.user.CreateUserResponse;
import com.flight.advisor.model.User;
import com.flight.advisor.service.user.CreateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserApi {

    private final CreateUser createUser;

    @PostMapping
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        final User newUser = UserConverter.toUserFromCreateUser(createUserRequest);

        return UserConverter.toCreateUserResponse(createUser.execute(newUser));
    }
}
