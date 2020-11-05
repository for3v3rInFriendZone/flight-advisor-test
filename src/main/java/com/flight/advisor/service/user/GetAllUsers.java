package com.flight.advisor.service.user;

import com.flight.advisor.model.User;
import com.flight.advisor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsers {

    private final UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }
}
