package com.example.quiz.Services;

import com.example.quiz.Dto.QuestionsDto;
import com.example.quiz.FrontEnd.Difficulty;
import com.example.quiz.FrontEnd.GameOptions;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
@Log
public class OngoingGameService {

    private GameOptions gameOptions;
    private int currentQuestionIndex;
    @Getter
    private int points;

    private List<QuestionsDto.QuestionDto>questions;

    @Autowired
    private QuizDataService quizDataService;

    public void init(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
        this.currentQuestionIndex = 0;
        this.points = 0;

        this.questions = quizDataService.getQuizQuestions(gameOptions);
    }
    public int getCurrentQuestionNumber() {
        return currentQuestionIndex+1;
    }
   public int getTotalQuestionNumber() {
        return questions.size();
   }
   public String getCurrentQuestion() {
        QuestionsDto.QuestionDto dto = questions.get(currentQuestionIndex);
        return dto.getQuestion();
   }
   public List<String> getCurrentQuestionAnswersInRandomOrder() {
        QuestionsDto.QuestionDto dto = questions.get(currentQuestionIndex);

        List<String> answers = new ArrayList<>();
        answers.add(dto.getCorrectAnswer());
        answers.addAll(dto.getIncorrectAnswers());

       Collections.shuffle(answers);
       return answers;
    }
   public boolean checkAnswerForCurrentQuestionAndUpdatePoints(String userAnswer) {
        QuestionsDto.QuestionDto dto = questions.get(currentQuestionIndex);
        boolean correct = dto.getCorrectAnswer().equals(userAnswer);
        log.info("correct:" + dto.getCorrectAnswer() );
       log.info("user:" + userAnswer );
        if(correct) {
            points++;
        }
        return correct;
   }
    public boolean proceedToNextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }
   public Difficulty difficulty() {
        return gameOptions.getDifficulty();
   }
   public String getCategoryName() {
       Optional<String> category = quizDataService.getQuizCategories().stream()
               .filter(categoryDto -> categoryDto.getId() == gameOptions.getCategoryId())
               .map(categoryDto -> categoryDto.getName())
               .findAny();
       return category.orElse(null);
   }
   }

