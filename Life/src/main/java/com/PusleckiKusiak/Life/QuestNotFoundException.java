package com.PusleckiKusiak.Life;

class QuestNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

QuestNotFoundException(Long id) {
    super("Could not find quest " + id);
  }
}