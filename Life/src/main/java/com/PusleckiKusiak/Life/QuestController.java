package com.PusleckiKusiak.Life;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.ui.Model;

@RestController
class QuestController {

  private final QuestRepository repository;

  QuestController(QuestRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

//  @GetMapping("/quests")
//  List<Quest> all() {
//    return repository.findAll();
//  }
  @GetMapping("/quests")
  CollectionModel<EntityModel<Quest>> all() {

    List<EntityModel<Quest>> quests = repository.findAll().stream()
      .map(quest -> new EntityModel<>(quest,
        linkTo(methodOn(QuestController.class).one(quest.getId())).withSelfRel(),
        linkTo(methodOn(QuestController.class).all()).withRel("quests")))
      .collect(Collectors.toList());

    return new CollectionModel<>(quests,
      linkTo(methodOn(QuestController.class).all()).withSelfRel());
  }
  
  @GetMapping("/test")
  String testMessage() {
	    return "TEST MESSAGE";
  }
  


  @PostMapping("/quests")
  Quest newQuest(@RequestBody Quest newQuest) {
    return repository.save(newQuest);
  }

  // Single item

//  @GetMapping("/quests/{id}")
//  Quest one(@PathVariable Long id) {
//
//	 return repository.findById(id).orElseThrow(() -> new QuestNotFoundException(id));
//  }
  
  @GetMapping("/quests/{id}")
  EntityModel<Quest> one(@PathVariable Long id) {

    Quest quest = repository.findById(id).orElseThrow(() -> new QuestNotFoundException(id));

    return new EntityModel<>(quest,
      linkTo(methodOn(QuestController.class).one(id)).withSelfRel(),
      linkTo(methodOn(QuestController.class).all()).withRel("quests"));
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
  
  
  // generate quest
  
//  @PutMapping("/generate")
//  Quest generateQuest(@RequestBody Quest newQuest, @PathVariable Long id) {
//
//    return repository.findById(id)
//      .map(quest -> {
//        quest.setName(newQuest.getName());
//        quest.setDescription(newQuest.getDescription());
//        quest.setPoints(newQuest.getPoints());
//        return repository.save(quest);
//      })
//      .orElseGet(() -> {
//        newQuest.setId(id);
//        return repository.save(newQuest);
//      });
//  }
//  
  
}