package com.Listy.project.arda.onur.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CommentRequest(
        @NotBlank(message = "{comment.request.comment.not.blank}")
        String comment) implements Serializable {
}
