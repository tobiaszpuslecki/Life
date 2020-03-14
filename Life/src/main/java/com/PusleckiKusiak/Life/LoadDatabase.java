package com.PusleckiKusiak.Life;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class LoadDatabase implements CommandLineRunner {
	private QuestRepository questRepository;
	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	LoadDatabase(BCryptPasswordEncoder bCryptPasswordEncoder,ApplicationUserRepository applicationUserRepository,QuestRepository questRepository){
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.applicationUserRepository =  applicationUserRepository;
		this.questRepository = questRepository;
	}
	@Override
	public void run(String... strings) throws Exception{
		 applicationUserRepository.save(new ApplicationUser("user",bCryptPasswordEncoder.encode("password")));
		 questRepository.save(new Quest("Quest1", "desc1", 1));
		 questRepository.save(new Quest("Quest2", "desc2", 1));
	}
}