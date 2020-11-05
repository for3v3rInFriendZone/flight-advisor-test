package com.flight.advisor.dto.comment;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UpdateCommentRequest {

    @NotBlank(message = "comment.text.blank")
    @Size(max = 1500, message = "comment.text.size")
    private String text;
}
