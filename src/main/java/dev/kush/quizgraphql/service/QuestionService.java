package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.model.Question;

import java.util.List;

/**
 * The QuestionService interface defines the methods for managing questions in a quiz.
 */
public interface QuestionService {
    List<Question> getAllQuestions();

    Question getQuestionById(Integer id);

    Question addQuestion(String type, String que, String option1, String option2, String option3, String option4, String ans, String difficulty);

    Question deleteQuestion(Integer id);
}
