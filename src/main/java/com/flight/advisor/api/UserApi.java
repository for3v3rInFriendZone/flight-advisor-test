package com.flight.advisor.api;

import com.flight.advisor.converters.UserConverter;
import com.flight.advisor.dto.user.CreateUserRequest;
import com.flight.advisor.dto.user.CreateUserResponse;
import com.flight.advisor.dto.user.SignInRequest;
import com.flight.advisor.dto.user.SignInResponse;
import com.flight.advisor.model.User;
import com.flight.advisor.service.user.CreateUser;
import com.flight.advisor.service.user.SignInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserApi {

    private final CreateUser createUser;
    private final SignInUser signInUser;

    @PostMapping("/sign-in")
    public SignInResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return signInUser.execute(signInRequest);
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        final User newUser = UserConverter.toUserFromCreateUser(createUserRequest);

        return UserConverter.toCreateUserResponse(createUser.execute(newUser));
    }
}
