package com.Listy.project.arda.onur.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CriteriaTypes {

    AUTHOR("Author"),
    COMMENT("Comment");

    private final String criteriaType;

}
