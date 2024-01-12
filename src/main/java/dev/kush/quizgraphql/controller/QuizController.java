package dev.kush.quizgraphql.controller;

import dev.kush.quizgraphql.model.QuestionWrapper;
import dev.kush.quizgraphql.model.Quiz;
import dev.kush.quizgraphql.model.Response;
import dev.kush.quizgraphql.model.Result;
import dev.kush.quizgraphql.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * The QuizController class is responsible for handling the API endpoints related to quizzes.
 */
@Controller
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @MutationMapping(value = "createQuiz")
    public Quiz createQuiz(
            @Argument String title,
            @Argument(value = "numberOfQuestions") int numQ,
            @Argument String type
    ) {
        return quizService.createQuiz(title, numQ, type.trim().toLowerCase());
    }

    @QueryMapping(value = "getQuiz")
    public List<QuestionWrapper> getQuizQuestions(@Argument(value = "quizId") Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @QueryMapping(value = "allQuiz")
    public List<Quiz> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    @QueryMapping(value = "submitQuiz")
    public Result submitQuiz(@Argument(value = "quizId") Integer id, @Argument(value = "userAnswers") List<Response> responses){
        return quizService.submitQuiz(id, responses);
    }

    @MutationMapping(value = "deleteQuiz")
    public Quiz deleteQuizById(@Argument(value = "quizId") Integer id){
        return quizService.deleteQuiz(id);
    }
}
