package com.PusleckiKusiak.Life;

class QuestNotFoundException extends RuntimeException {

  QuestNotFoundException(Long id) {
    super("Could not find quest " + id);
  }
}