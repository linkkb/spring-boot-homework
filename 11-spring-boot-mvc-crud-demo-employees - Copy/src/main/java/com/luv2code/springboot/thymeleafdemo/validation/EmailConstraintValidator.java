package com.luv2code.springboot.thymeleafdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {

    private String emailSuffix;

    @Override
    public void initialize(EmailConstraint theConstraint) {
        emailSuffix = theConstraint.value();
    }

    @Override
    public boolean isValid(String email, //user input
                           ConstraintValidatorContext constraintValidatorContext) { //helper class

        boolean result;
        if (email != null) {
            result = email.endsWith(emailSuffix);
        } else {
            result = true;
        }
        return result;
    }
}
