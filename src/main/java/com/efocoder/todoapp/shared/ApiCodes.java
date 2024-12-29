package com.efocoder.todoapp.shared;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ApiCodes {
//    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code" ),
//    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Current password is incorrect"),
//    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN , "Account locked"),
//    PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST , "Passwords do not match"),
//    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN , "Account disabled"),
    INVALID_CREDENTIALS(20, HttpStatus.UNAUTHORIZED , "Invalid credentials"),
    SUCCESS(1, HttpStatus.OK , "Successful"),
    NO_BODY(0, HttpStatus.BAD_REQUEST , "No request body supplied"),
    VALIDATION_ERROR(2, HttpStatus.BAD_REQUEST , "Validation error"),
    ROUTE_NOT_FOUND(21, HttpStatus.NOT_FOUND , "Route not found"),
    ;

    private final int code;

    private final String message;

    private final HttpStatus httpStatus;

    ApiCodes(int code,  HttpStatus httpStatus, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
