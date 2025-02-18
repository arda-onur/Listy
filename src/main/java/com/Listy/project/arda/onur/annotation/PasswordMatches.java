package com.Listy.project.arda.onur.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Password does not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
