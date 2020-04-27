package com.PusleckiKusiak.Life.controler;

import java.util.List;
import java.util.Optional;


import com.PusleckiKusiak.Life.ScheduledTasks;
import com.PusleckiKusiak.Life.entity.DailyQuest;
import com.PusleckiKusiak.Life.entity.Quest;
import com.PusleckiKusiak.Life.entity.User;
import com.PusleckiKusiak.Life.entity.WeeklyQuest;
import com.PusleckiKusiak.Life.exception.QuestNotFoundException;
import com.PusleckiKusiak.Life.repository.QuestRepository;
import com.PusleckiKusiak.Life.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
class QuestController {
  private static final Logger log = LoggerFactory.getLogger(QuestController.class);
  private final QuestRepository repository;
  private final UserRepository userRepository;
  private DailyQuest dailyQuest;
  private WeeklyQuest weeklyQuest;

  QuestController(QuestRepository repository,
                  DailyQuest dailyQuest,
                  UserRepository userRepository,
                  WeeklyQuest weeklyQuest) {
    this.repository = repository;
    this.dailyQuest = dailyQuest;
    this.userRepository = userRepository;
    this.weeklyQuest = weeklyQuest;
  }
  
  @GetMapping("/quests")
  public List<Quest> getAll(){
	  return repository.findAll();
  }
  @PostMapping("/quests")
  Quest newQuest(@RequestBody Quest newQuest) {
    return repository.save(newQuest);
  }

  @GetMapping("/quests/{id}")
  Optional<Quest> one(@PathVariable Long id) {
    if(repository.existsQuestById(id)){
      return repository.findById(id);
    }
    else throw  new QuestNotFoundException("Quest not found");
  }

  @PutMapping("/quests/{id}")
  Optional<Quest> replaceQuest(@RequestBody Quest newQuest, @PathVariable Long id) {
    if(repository.existsQuestById(id)){
      return repository.findById(id)
              .map(quest -> {
                quest.setName(newQuest.getName());
                quest.setDescription(newQuest.getDescription());
                quest.setPoints(newQuest.getPoints());
                return repository.save(quest);
              });
    }else{
      throw new QuestNotFoundException("Quest not found");
    }
  }

  @DeleteMapping("/quests/{id}")
  void deleteQuest(@PathVariable Long id) {
    if(repository.existsQuestById(id)){
      repository.deleteById(id);
    }else{
      throw new QuestNotFoundException("Quest not found");
    }
  }

  @GetMapping("/daily/{userName}")
  DailyQuest getDailyQuest(@PathVariable String userName){

    User tmp = userRepository.findByUsername(userName);
    if(!tmp.isDoneDaily()){
      return dailyQuest;
    }
    else throw new QuestNotFoundException("User already finished daily quest");
  }
  @PostMapping("/daily/{userName}")
  void finishDailyQuest(@PathVariable String userName){
      User tmp = userRepository.findByUsername(userName);
      if(!tmp.isDoneDaily()) {
        tmp.setXp(tmp.getXp() + dailyQuest.getPoints());
        tmp.setDoneDaily(true);
        userRepository.save(tmp);
      }
      else{
        throw new QuestNotFoundException("User already finished daily quest");
      }
  }
  @GetMapping("/weekly/{userName}")
  WeeklyQuest getWeeklyQuest(@PathVariable String userName){

    User tmp = userRepository.findByUsername(userName);
    if(!tmp.isDoneWeekly()){
      return weeklyQuest;
    }
    else throw new QuestNotFoundException("User already finished weekly quest");
  }
  @PostMapping("/weekly/{userName}")
  void finishWeeklyQuest(@PathVariable String userName){
    User tmp = userRepository.findByUsername(userName);
    if(!tmp.isDoneWeekly()) {
      tmp.setXp(tmp.getXp() + weeklyQuest.getPoints());
      tmp.setDoneWeekly(true);
      userRepository.save(tmp);
    }
    else{
      throw new QuestNotFoundException("User already finished weekly quest");
    }
  }

}
