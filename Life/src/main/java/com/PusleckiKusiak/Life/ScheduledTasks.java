package com.PusleckiKusiak.Life;

import com.PusleckiKusiak.Life.entity.DailyQuest;


import com.PusleckiKusiak.Life.entity.Quest;
import com.PusleckiKusiak.Life.entity.User;
import com.PusleckiKusiak.Life.entity.WeeklyQuest;
import com.PusleckiKusiak.Life.repository.QuestRepository;
import com.PusleckiKusiak.Life.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Transactional
@Service
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private DailyQuest dailyQuest;
    private WeeklyQuest weeklyQuest;
    private Random rand;
    private final QuestRepository questRepository;
    private final UserRepository userRepository;
    ScheduledTasks(DailyQuest dailyQuest,
                   QuestRepository questRepository,
                   UserRepository userRepository,
                   WeeklyQuest weeklyQuest){
        this.dailyQuest = dailyQuest;
        this.weeklyQuest = weeklyQuest;
        this.rand = new Random();
        this.questRepository = questRepository;
        this.userRepository = userRepository;
    }

    // for debug purposes 15 seconds

    @Scheduled(cron = "0/15 * * * * ?")
    public void generateDailyTask(){
        if(questRepository.count() != 0){
            long id;
            Quest quest;
            id  = ThreadLocalRandom.current().nextLong(1,questRepository.count());
            quest = questRepository.getOne(id);
            dailyQuest.setName(quest.getName());
            dailyQuest.setDescription(quest.getDescription());
            dailyQuest.setPoints(quest.getPoints());
            List<User> users = userRepository.findAll();
            for (User user : users){
                user.setDoneDaily(false);
            }
        }
    }
    // for debug purposes 20 seconds
    @Scheduled(cron = "0/20 * * * * ?")
    public void generateWeeklyTask(){
        if(questRepository.count() != 0){
            long id;
            Quest quest;
            id  = ThreadLocalRandom.current().nextLong(1,questRepository.count());
            quest = questRepository.getOne(id);
            weeklyQuest.setName(quest.getName());
            weeklyQuest.setDescription(quest.getDescription());
            weeklyQuest.setPoints(quest.getPoints()*4);
            List<User> users = userRepository.findAll();
            for (User user : users){
                user.setDoneWeekly(false);
            }
        }
    }

}
