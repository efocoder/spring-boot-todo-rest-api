package com.efocoder.todoapp.exception;

public class UniqueConstraintViolationException extends RuntimeException{
    public UniqueConstraintViolationException(String message) { super(message); }
}
