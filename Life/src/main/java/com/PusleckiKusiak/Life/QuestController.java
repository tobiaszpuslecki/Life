package com.PusleckiKusiak.Life;

import java.util.List;
import java.util.Optional;
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

  private final QuestRepository repository;

  QuestController(QuestRepository repository) {
    this.repository = repository;
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
	  return repository.findById(id);
  }

  @PutMapping("/quests/{id}")
  Quest replaceQuest(@RequestBody Quest newQuest, @PathVariable Long id) {

    return repository.findById(id)
      .map(quest -> {
        quest.setName(newQuest.getName());
        quest.setDescription(newQuest.getDescription());
        quest.setPoints(newQuest.getPoints());
        return repository.save(quest);
      })
      .orElseGet(() -> {
        newQuest.setId(id);
        return repository.save(newQuest);
      });
  }

  @DeleteMapping("/quests/{id}")
  void deleteQuest(@PathVariable Long id) {
    repository.deleteById(id);
  }
}