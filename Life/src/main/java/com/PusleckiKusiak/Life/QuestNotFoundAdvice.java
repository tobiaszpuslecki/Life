package com.PusleckiKusiak.Life;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class QuestNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(QuestNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String questNotFoundHandler(QuestNotFoundException ex) {
    return ex.getMessage();
  }
}