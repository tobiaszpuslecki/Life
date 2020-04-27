package com.PusleckiKusiak.Life.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public
class Quest {

  private @Id @GeneratedValue Long id;
  private String name;
  private String description;
  private int points;
  private Boolean isDone;

  public  Quest() {}

  public Quest(String name, String descrition, int points) {
    this.name = name;
    this.description = descrition;
    this.points = points;
    isDone = false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public Boolean getDone() {
    return isDone;
  }

  public void setDone(Boolean done) {
    isDone = done;
  }
}