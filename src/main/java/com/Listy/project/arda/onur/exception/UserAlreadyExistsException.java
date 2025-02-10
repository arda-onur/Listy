package com.Listy.project.arda.onur.exception;

import lombok.Getter;


@Getter
public class UserAlreadyExistsException extends RuntimeException{

    private final String username;

    public UserAlreadyExistsException(String username) {
        super(username);
        this.username=username;
    }
}
