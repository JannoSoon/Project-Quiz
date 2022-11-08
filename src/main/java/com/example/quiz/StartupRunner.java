package com.example.quiz;

import com.example.quiz.Services.QuizDataService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Log
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private QuizDataService quizDataService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Executing startup actions...");

        quizDataService.getQuizCategories();









/*
        playerRepository.save(new PlayerEntity("Meiko"));
        playerRepository.save(new PlayerEntity("Krista"));
        playerRepository.save(new PlayerEntity("Hanna"));


        log.info("List of players from Database");
        List<PlayerEntity> playersFromDb = playerRepository.findAll();
        for (PlayerEntity player : playersFromDb) {
            log.info("Retrieved player" + player);

        }

 */
    }
}