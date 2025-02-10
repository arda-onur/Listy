package com.Listy.project.arda.onur.annotation;

import com.Listy.project.arda.onur.request.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordMatches, RegisterRequest> {


    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registerRequest.password().equals(registerRequest.confirmedPassword());
    }


}
