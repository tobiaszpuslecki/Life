package com.PusleckiKusiak.Life.exception;

public class ApiRegisterException extends RuntimeException{
    public ApiRegisterException(String message) {
        super(message);
    }

    public ApiRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}