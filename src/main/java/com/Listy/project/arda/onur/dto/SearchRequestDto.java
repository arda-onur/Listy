package com.Listy.project.arda.onur.dto;

import java.io.Serializable;

public record SearchRequestDto(
        String criteria,
        String search) implements Serializable {
}
