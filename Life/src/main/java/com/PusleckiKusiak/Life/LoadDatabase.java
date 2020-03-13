package com.PusleckiKusiak.Life;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.PusleckiKusiak.Life.*;
@Configuration
@Slf4j
class LoadDatabase {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	LoadDatabase(BCryptPasswordEncoder bCryptPasswordEncoder){
		
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
  @Bean
  CommandLineRunner initDatabase(QuestRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Quest("Quest1", "desc1", 1)));
      log.info("Preloading " + repository.save(new Quest("Quest2", "desc2", 2)));
    };
  }
  @Bean
  CommandLineRunner initDatabaseUser(ApplicationUserRepository repository) {
    return args -> {
    	ApplicationUser admin = new ApplicationUser();
    	admin.setUsername("user");
    	admin.setPassword(bCryptPasswordEncoder.encode("password"));
    	log.info("Preloading " + repository.save(admin));
    };
  }
}