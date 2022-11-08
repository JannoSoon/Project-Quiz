package com.example.quiz.FrontEnd;

import lombok.Data;

@Data
public class GameOptions {
        private int numberOfQuestions = 5;
        private Difficulty difficulty;
        private int categoryId;
        private int points;
}
