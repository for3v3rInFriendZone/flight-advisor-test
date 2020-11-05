package com.flight.advisor.api;

import com.flight.advisor.converters.CommentConverter;
import com.flight.advisor.dto.comment.CommentResponse;
import com.flight.advisor.dto.comment.UpdateCommentRequest;
import com.flight.advisor.model.Comment;
import com.flight.advisor.service.comment.RemoveComment;
import com.flight.advisor.service.comment.UpdateComment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentApi {

    private final RemoveComment removeComment;
    private final UpdateComment updateComment;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable UUID id) {
        removeComment.execute(id);
    }

    @PatchMapping("/{id}")
    public CommentResponse updateComment(@PathVariable UUID id, @RequestBody @Valid UpdateCommentRequest updateCommentRequest) {
        final Comment updatedComment = updateComment.execute(id, updateCommentRequest.getText());

        return CommentConverter.toCommentResponse(updatedComment);
    }
}
