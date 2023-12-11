package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.model.Question;
import dev.kush.quizgraphql.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Question not found.")
        );
    }

    @Override
    public Question addQuestion(String type, String que, String option1, String option2, String option3, String option4, String ans, String difficulty) {
        Question question = new Question(type, que, option1, option2,option3, option4, ans, difficulty);

        questionRepository.save(question);
        return question;
    }

    @Override
    public Question deleteQuestion(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find Question."));

        questionRepository.delete(question);

        return question;
    }
}
