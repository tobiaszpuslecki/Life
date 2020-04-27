package com.PusleckiKusiak.Life.exception;

public class QuestNotFoundException extends RuntimeException{
    public QuestNotFoundException(String message) {
        super(message);
    }

    public QuestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}