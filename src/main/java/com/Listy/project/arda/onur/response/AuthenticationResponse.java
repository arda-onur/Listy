package com.Listy.project.arda.onur.response;


import lombok.Builder;

import java.io.Serializable;


@Builder
public record AuthenticationResponse (
        String token) implements Serializable {
}
