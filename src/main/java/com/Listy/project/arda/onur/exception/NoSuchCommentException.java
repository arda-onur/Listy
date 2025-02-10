package com.Listy.project.arda.onur.exception;

import lombok.Getter;


@Getter

public class NoSuchCommentException extends RuntimeException {

    private final String comment;

    public NoSuchCommentException(String message) {
        super(message);
        this.comment = message;
    }
}
