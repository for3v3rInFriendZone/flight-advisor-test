package com.flight.advisor.dto.comment;

import com.flight.advisor.util.Constants;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UpdateCommentRequest {

    @NotBlank(message = "comment.text.blank")
    @Size(max = Constants.COMMENT_MAX_SIZE, message = "comment.text.size")
    private String text;
}
