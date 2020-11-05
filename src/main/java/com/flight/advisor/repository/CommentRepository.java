package com.flight.advisor.repository;

import com.flight.advisor.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends BaseRepository<Comment> {
}
