package com.flight.advisor.converters;

import com.flight.advisor.dto.user.CreateUserRequest;
import com.flight.advisor.dto.user.CreateUserResponse;
import com.flight.advisor.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public CreateUserResponse toCreateUserResponse(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .build();
    }

    public User toUserFromCreateUser(CreateUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
