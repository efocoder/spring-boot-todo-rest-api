package com.efocoder.todoapp.exception;

import com.efocoder.todoapp.shared.ApiCodes;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
@Order(ExceptionHandlerOrder.GLOBAL)
public class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .code(ApiCodes.INVALID_CREDENTIALS.getCode())
                        .error(ApiCodes.INVALID_CREDENTIALS.getMessage())
                        .build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpRequestMethodNotSupportedException exp){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .code(ApiCodes.ROUTE_NOT_FOUND.getCode())
                        .error(ApiCodes.ROUTE_NOT_FOUND.getMessage())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpMessageNotReadableException exp){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .code(ApiCodes.NO_BODY.getCode())
                        .message(ApiCodes.NO_BODY.getMessage())
                        .build());
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .code(ApiCodes.FAILURE.getCode())
                        .message(String.format("Invalid parameter: '%s'. Expected type: '%s'.",
                                ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()))
                        .build());
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .code(ApiCodes.RECORD_NOT_FOUND.getCode())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException exp){
        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .message(exp.getMessage())
                        .build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp){
//        System.out.println(exp.getMessage());
        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .message("Internal error, contact admin")
                        .build());
    }
}
