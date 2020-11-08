package com.flight.advisor.service.user;

import com.flight.advisor.exception.user.UsernameAlreadyTakenException;
import com.flight.advisor.model.User;
import com.flight.advisor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user) {
        log.info("Trying to register new user * {} *", user.getUsername());

        if (isUsernameTaken(user.getUsername())) {
            throw new UsernameAlreadyTakenException(user.getUsername());
        }

        final String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setType(User.UserType.REGULAR);
        user.setPassword(hashedPassword);

        final User newUser = userRepository.save(user);

        log.info("User: * {} * was successfully created.", user.getUsername());
        return newUser;
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findOneByUsername(username).isPresent();
    }
}
