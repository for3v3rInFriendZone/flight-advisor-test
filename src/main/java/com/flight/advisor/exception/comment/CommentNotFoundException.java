package com.flight.advisor.exception.comment;

import com.flight.advisor.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Slf4j
public class CommentNotFoundException extends GenericException {
    private static final String NOT_FOUND_COMMENT_KEY = "comment.not.found";

    public CommentNotFoundException(UUID id) {
        super(NOT_FOUND_COMMENT_KEY, HttpStatus.NOT_FOUND);

        log.error("Comment with an Id * {} * was not found", id);
    }
}
