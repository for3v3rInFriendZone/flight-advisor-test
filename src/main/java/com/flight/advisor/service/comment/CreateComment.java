package com.flight.advisor.service.comment;

import com.flight.advisor.model.Comment;
import com.flight.advisor.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateComment {

    private final CommentRepository commentRepository;

    public Comment execute(Comment comment) {
        return commentRepository.save(comment);
    }
}
