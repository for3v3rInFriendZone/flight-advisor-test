package com.flight.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserType type;

    public enum UserType {
        ADMIN,
        REGULAR
    }
}


