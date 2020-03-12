package com.PusleckiKusiak.Life;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(QuestRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Quest("Quest1", "desc1", 1)));
      log.info("Preloading " + repository.save(new Quest("Quest2", "desc2", 2)));
    };
  }
}