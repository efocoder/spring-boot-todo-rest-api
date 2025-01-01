package com.efocoder.todoapp.exception;

import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.validator.ValidStatusEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;

public class StatusEnumValidator implements ConstraintValidator<ValidStatusEnum, StatusEnum> {
    @Override
    public boolean isValid(StatusEnum value, ConstraintValidatorContext context) {
        return value != null && EnumSet.allOf(StatusEnum.class).contains(value);
    }
}
