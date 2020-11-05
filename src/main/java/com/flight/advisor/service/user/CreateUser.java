package com.flight.advisor.service.user;

import com.flight.advisor.exception.user.UsernameAlreadyTakenException;
import com.flight.advisor.model.User;
import com.flight.advisor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateUser {

    private final UserRepository userRepository;

    public User execute(User user) {
        if (isUsernameTaken(user.getUsername())) {
            throw new UsernameAlreadyTakenException(user.getUsername());
        }

        user.setType(User.UserType.REGULAR);
        return userRepository.save(user);
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findOneByUsername(username).isPresent();
    }
}
