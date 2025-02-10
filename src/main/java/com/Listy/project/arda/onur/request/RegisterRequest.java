package com.Listy.project.arda.onur.request;


import com.Listy.project.arda.onur.annotation.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import java.io.Serializable;


@Builder
@PasswordMatches
public record RegisterRequest(
        @NotBlank(message = "{register.request.username.not.blank}")
        @Size(message = "{register.request.username.size.message}", min = 4, max = 255)
        String username,

        @NotBlank(message = "{register.request.password.not.blank}")
        @Size(message = "{register.request.password.size.message}", min = 8, max = 255)
        String password,

        @NotBlank(message = "{register.request.password.not.blank}")
        @Size(message = "{register.request.password.size.message}", min = 8, max = 255)
        String confirmedPassword) implements Serializable
{


}
