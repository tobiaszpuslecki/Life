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
		 questRepository.save(new Quest("Librarian", "Read 50 pages of the book", 100));
		 questRepository.save(new Quest("Marathon", "Run 5 kilometers", 100));
		 questRepository.save(new Quest("Lance, is that you?", "Ride a bike 30 kilometers", 100));
		 questRepository.save(new Quest("Schwarzenegger", "Do 15 push-ups", 100));
		 questRepository.save(new Quest("Code IT!", "Write 100 lines of code", 100));
		 questRepository.save(new Quest("Netfix and chill", "Watch 4 episodes of the favourite series", 100));
		 questRepository.save(new Quest("Youth friend", "Solve 5 accounting tasks", 100));
		 questRepository.save(new Quest("TV and.. chill?", "Watch TV for 60 minutes", 100));
		 questRepository.save(new Quest("PCMR", "Play computer game for 60 minutes", 100));
		 questRepository.save(new Quest("Yeah! Science", "Study for your next class", 100));
		 questRepository.save(new Quest("Finally some good food", "Ugotuj obiad", 100));
		 questRepository.save(new Quest("We have to cook", "Try a new food", 100));
		 questRepository.save(new Quest("Monter hunter", "Kill a bug", 100));
		 questRepository.save(new Quest("Pot planter", "Plant a plant", 100));
		 questRepository.save(new Quest("Music to my ears", "Listen to your favourite music", 100));
	}
}