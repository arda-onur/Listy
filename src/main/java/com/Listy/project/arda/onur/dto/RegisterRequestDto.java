package com.Listy.project.arda.onur.dto;

import java.io.Serializable;

public record RegisterRequestDto(
        String username,
        String password) implements Serializable {
}
