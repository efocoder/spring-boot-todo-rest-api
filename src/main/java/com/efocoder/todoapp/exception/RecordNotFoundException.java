package com.efocoder.todoapp.exception;

public class RecordNotFoundException extends  RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
