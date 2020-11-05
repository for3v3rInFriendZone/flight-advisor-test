package com.flight.advisor.api;

import com.flight.advisor.model.User;
import com.flight.advisor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }
}
