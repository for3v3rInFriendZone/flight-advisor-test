package com.flight.advisor.repository;

import com.flight.advisor.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findOneByUsername(String username);
}
