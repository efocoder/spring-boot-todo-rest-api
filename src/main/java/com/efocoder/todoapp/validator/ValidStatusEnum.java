package com.efocoder.todoapp.validator;

import com.efocoder.todoapp.exception.StatusEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = StatusEnumValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatusEnum {
    String message() default "Invalid status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
