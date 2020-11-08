package com.flight.advisor.service.comment;

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
public class UpdateComment {

    private final CommentRepository commentRepository;
    private final GetCommentById getCommentById;

    public Comment execute(UUID id, String text) {
        log.info("Trying to update Comment: * {} * with a text: {}", id, text);

        final Comment oldComment = getCommentById.execute(id);

        oldComment.setText(text);
        final Comment updatedComment = commentRepository.save(oldComment);

        log.info("Comment updated successfuly to: {}", text);
        return updatedComment;
    }
}
