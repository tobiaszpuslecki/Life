package com.PusleckiKusiak.Life;


import com.PusleckiKusiak.Life.entity.User;
import com.PusleckiKusiak.Life.entity.Quest;
import com.PusleckiKusiak.Life.repository.UserRepository;
import com.PusleckiKusiak.Life.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class LoadDatabase implements CommandLineRunner {
	private QuestRepository questRepository;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	LoadDatabase(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, QuestRepository questRepository){
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
		this.questRepository = questRepository;
	}
	@Override
	public void run(String... strings) throws Exception{
		 userRepository.save(new User("user",bCryptPasswordEncoder.encode("password")));
		 questRepository.save(new Quest("Librarian", "Przeczytaj 50 stron książki", 100));
		 questRepository.save(new Quest("Quest2", "Przebiegnij 5 kilometrów", 100));
		 questRepository.save(new Quest("Lance, is that you?", "Przejedź na rowerze 30 kilometrów", 100));
		 questRepository.save(new Quest("Quest4", "Zrób 15 pompek", 100));
		 questRepository.save(new Quest("Code IT!", "Napisz 100 linii kodu", 100));
		 questRepository.save(new Quest("Quest3", "Obejrzyj 2 odcinki serialu", 100));
		 questRepository.save(new Quest("Grow a beard", "Rozwiąż 5 zadań rachunkowych", 100));
		 questRepository.save(new Quest("Quest5", "Oglądaj TV przez 60 minut", 100));
		 questRepository.save(new Quest("Quest5", "Zagraj na komputerze przez 45 minut", 100));
		 questRepository.save(new Quest("Yeah! Science", "Opracuj 5 zagadnień na następne zajęcia", 100));
		 questRepository.save(new Quest("Quest5", "Pozmywaj naczynia", 100));
		 questRepository.save(new Quest("Quest5", "Poodkurzaj", 100));
		 questRepository.save(new Quest("Quest5", "Zrób pranie", 100));
		 questRepository.save(new Quest("Quest5", "Umyj podłogę", 100));
		 questRepository.save(new Quest("Finally some good food", "Ugotuj obiad", 100));
		 questRepository.save(new Quest("We have to cook", "Try a new food", 100));
		 questRepository.save(new Quest("Quest5", "Umyj podłogę", 100));
	}
}