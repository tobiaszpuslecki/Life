package com.PusleckiKusiak.Life.repository;


import com.PusleckiKusiak.Life.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    boolean existsQuestById(Long id);
}