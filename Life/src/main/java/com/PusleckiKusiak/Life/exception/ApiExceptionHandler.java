package com.PusleckiKusiak.Life.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRegisterException.class})
    public ResponseEntity<Object> handleApiRegisterException(ApiRegisterException e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                e.getMessage(),
                conflict,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException,conflict);
    }
    @ExceptionHandler(value = {QuestNotFoundException.class})
    public  ResponseEntity<Object> handleApiQuestNotFoundException(QuestNotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException,notFound);
    }
}