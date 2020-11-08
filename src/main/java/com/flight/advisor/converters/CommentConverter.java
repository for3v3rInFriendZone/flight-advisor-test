package com.flight.advisor.converters;

import com.flight.advisor.dto.comment.CommentResponse;
import com.flight.advisor.dto.comment.CreateCommentRequest;
import com.flight.advisor.dto.comment.CreateCommentResponse;
import com.flight.advisor.model.Comment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentConverter {

    public Comment toCommentFromCreateComment(CreateCommentRequest createCommentRequest) {
        return Comment.builder()
                .text(createCommentRequest.getText())
                .build();
    }

    public CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return CreateCommentResponse.builder()
                .id(comment.getId())
                .build();
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }
}