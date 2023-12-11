package dev.kush.quizgraphql.controller;

import dev.kush.quizgraphql.model.Question;
import dev.kush.quizgraphql.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller class for managing questions.
 */
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @QueryMapping(value = "allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @QueryMapping(value = "findOne")
    public Question getQuestionById(@Argument Integer id){
        return questionService.getQuestionById(id);
    }


    @MutationMapping(value = "addOne")
    public Question addQuestion(
            @Argument String type,
            @Argument String que,
            @Argument String option1,
            @Argument String option2,
            @Argument String option3,
            @Argument String option4,
            @Argument String ans,
            @Argument String difficulty
            ){
        return questionService.addQuestion(type, que, option1,option2,option3,option4,ans, difficulty);
    }

    @MutationMapping(value = "deleteOne")
    public Question deleteQuestionById(@Argument Integer id) {
        return questionService.deleteQuestion(id);
    }
}
