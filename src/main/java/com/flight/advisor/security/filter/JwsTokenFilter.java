package com.flight.advisor.security.filter;

import com.flight.advisor.security.jwt.JwsTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class JwsTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwsTokenUtils jwsUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwtToken = parseJwt(request);

        if (jwtToken != null && jwsUtils.isJwsTokenValid(jwtToken)) {
            UUID sub = UUID.fromString(jwsUtils.getSubFromJwsToken(jwtToken));
            Set<SimpleGrantedAuthority> userType = jwsUtils.getUserTypeFromJwsToken(jwtToken);

            PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
                    sub, null, userType);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
