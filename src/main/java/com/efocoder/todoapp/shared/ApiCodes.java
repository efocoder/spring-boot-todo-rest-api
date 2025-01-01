package com.efocoder.todoapp.shared;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ApiCodes {
    INVALID_CREDENTIALS(20, HttpStatus.UNAUTHORIZED , "Invalid credentials"),
    INVALID_TOKEN(25, HttpStatus.UNAUTHORIZED , "Invalid access token"),
    SUCCESS(1, HttpStatus.OK , "Request successful"),
    FAILURE(0, HttpStatus.OK , "Request failed"),
    NO_BODY(10, HttpStatus.BAD_REQUEST , "No request body supplied"),
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
