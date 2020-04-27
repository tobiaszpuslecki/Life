package com.PusleckiKusiak.Life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LifeApplication {

  public static void main(String... args) {
    SpringApplication.run(LifeApplication.class, args);
  }
  
}