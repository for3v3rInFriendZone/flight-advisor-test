package com.flight.advisor.security.jwt;

import com.flight.advisor.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwsTokenUtils {

    private static final String USER_TYPE_CLAIM = "user_type";

    @Value("${jws.token.expiration}")
    private Long tokenExpiration;

    @Value("${jws.token.secret}")
    private String tokenSecret;

    public String generateJwsToken(String sub, User.UserType userType) {
        final Date now = Date.from(Instant.now());
        final Date expirationDate = Date.from(Instant.now().plus(tokenExpiration, ChronoUnit.MILLIS));

        return Jwts.builder()
                .setSubject(sub)
                .claim(USER_TYPE_CLAIM, userType)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    public String getSubFromJwsToken(String token) {
        return Jwts.parser().setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isJwsTokenValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(tokenSecret)
                    .parseClaimsJws(authToken);

            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateUserTypeFromToken(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(""));
    }


    public Set<SimpleGrantedAuthority> getUserTypeFromJwsToken(String token) {
        final String userType = Jwts.parser().setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody()
                .get(USER_TYPE_CLAIM, String.class);

        return Stream.of(userType)
                .map(SimpleGrantedAuthority::new)
                .collect(toSet());
    }
}
