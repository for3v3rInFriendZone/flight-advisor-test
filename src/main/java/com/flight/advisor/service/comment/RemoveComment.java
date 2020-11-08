package com.flight.advisor.service.comment;

import com.flight.advisor.exception.comment.CommentNotFoundException;
import com.flight.advisor.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RemoveComment {

    private final CommentRepository commentRepository;

    public void execute(UUID commentId) {
        log.info("Trying to delete a comment with an Id: {}", commentId);

        if (!commentRepository.existsById(commentId)) {
            throw new CommentNotFoundException(commentId);
        }

        commentRepository.deleteById(commentId);
        log.info("Comment successfully deleted");
    }
}
