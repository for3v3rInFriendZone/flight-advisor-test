package com.flight.advisor.security.service;

import com.flight.advisor.exception.user.UserNotFoundException;
import com.flight.advisor.model.User;
import com.flight.advisor.repository.UserRepository;
import com.flight.advisor.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findOneByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return getJwtUser(user);
    }

    private SecurityUser getJwtUser(User user) {
        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                getUserType(user.getType().name())
        );
    }

    private Set<SimpleGrantedAuthority> getUserType(String userType) {
        return Stream.of(userType)
                .map(SimpleGrantedAuthority::new)
                .collect(toSet());
    }
}
