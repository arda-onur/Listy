package com.Listy.project.arda.onur.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;


@Builder
public record AuthenticationRequest(

        @NotBlank(message = "{authenticaton.request.username.not.blank}")
        String username,

        @NotBlank(message = "{authenticaton.request.password.not.blank}")
        String password) implements Serializable {


}
