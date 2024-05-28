package com.example.restorationmanagement.exception;

public class InternalServerException extends RuntimeException{
    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable ex) {
        super(message, ex);
    }

    
}
