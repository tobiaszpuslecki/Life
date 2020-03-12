package com.PusleckiKusiak.Life;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Quest {

  private @Id @GeneratedValue Long id;
  private String name;
  private String description;
  private int points;
  private Boolean isDone;

  Quest() {}

  Quest(String name, String descrition, int points) {
    this.name = name;
    this.description = descrition;
    this.points = points;
    isDone = false;
  }
}