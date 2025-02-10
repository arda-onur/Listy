package com.Listy.project.arda.onur.dto;

import java.io.Serializable;

public record AuthenticationRequestDto(
        String username,
        String password) implements Serializable {
}
