package com.luv2code.springboot.thymeleafdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailConstraintValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD }) //where can this be applied
@Retention(RetentionPolicy.RUNTIME) //when is this processed / used
public @interface EmailConstraint {
    public String value() default ".com"; //default constraint text
    public String message() default "must end with .com"; //default error message
    public Class<?>[] groups() default {}; //define default groups (groups of constraints)
    public Class<? extends Payload>[] payload() default {};

}
