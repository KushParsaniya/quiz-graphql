package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.model.QuestionWrapper;
import dev.kush.quizgraphql.model.Quiz;
import dev.kush.quizgraphql.model.Response;
import dev.kush.quizgraphql.model.Result;

import java.util.List;


/**
 * The QuizService interface provides methods for managing quizzes.
 */
public interface QuizService {
    Quiz createQuiz(String title, int numQ, String type);

    List<QuestionWrapper> getQuizQuestions(Integer id);

    List<Quiz> getAllQuiz();

    Result submitQuiz(Integer id, List<Response> responses);

    Quiz deleteQuiz(Integer id);
}
