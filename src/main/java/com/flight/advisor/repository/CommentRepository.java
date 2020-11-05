package com.flight.advisor.repository;

import com.flight.advisor.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {

    @Query(value = "" +
            "SELECT * FROM comment " +
            "WHERE city_id = :id " +
            "ORDER BY created_date DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<Comment> findAllByCityId(String id, Integer limit);
}
