package com.Listy.project.arda.onur.exception;

import lombok.Getter;


@Getter
public class NoSuchAuthorException extends RuntimeException {

    private final String author;

    public NoSuchAuthorException(String author) {
        super(author);
        this.author = author;
    }
}
