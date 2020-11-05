package com.flight.advisor.service.comment;

import com.flight.advisor.exception.comment.CommentNotFoundException;
import com.flight.advisor.model.Comment;
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
public class GetCommentById {

    private final CommentRepository commentRepository;

    public Comment execute(UUID id)  {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
