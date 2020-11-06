package com.flight.advisor.service.user;

import com.flight.advisor.dto.user.SignInRequest;
import com.flight.advisor.dto.user.SignInResponse;
import com.flight.advisor.exception.user.BadCredentialsException;
import com.flight.advisor.model.User;
import com.flight.advisor.security.jwt.JwsTokenUtils;
import com.flight.advisor.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SignInUser {

    private final AuthenticationManager authenticationManager;
    private final JwsTokenUtils jwsTokenUtils;

    public SignInResponse execute(SignInRequest signInRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final SecurityUser userPrincipal = (SecurityUser) authentication.getPrincipal();
        final String sub = userPrincipal.getSub().toString();
        final String userType = jwsTokenUtils.generateUserTypeFromToken(userPrincipal.getAuthorities());

        final String token = jwsTokenUtils.generateJwsToken(sub, User.UserType.valueOf(userType));

        return SignInResponse.builder()
                .token(token)
                .build();
    }
}
