package com.flight.advisor.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service("userTypePermission")
public class CheckUserTypePermission {

    public boolean hasAny(String... userTypes) {
        final Collection<? extends GrantedAuthority> userTypesAuthority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return Arrays.stream(userTypes)
                .anyMatch(permissionValue -> userTypesAuthority.contains(new SimpleGrantedAuthority(permissionValue)));
    }
}
