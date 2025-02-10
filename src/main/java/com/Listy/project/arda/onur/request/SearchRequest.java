package com.Listy.project.arda.onur.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;


public class SearchRequest implements Serializable {
    @NotBlank(message = "{search.request.criteria.not.blank}")
    private String criteria;
    @NotBlank(message = "{search.request.search.not.blank}")
    private String search;
}
