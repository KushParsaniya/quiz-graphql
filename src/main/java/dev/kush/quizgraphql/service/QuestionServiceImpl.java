package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.model.Question;
import dev.kush.quizgraphql.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the QuestionService interface.
 */
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

    /**
     * Retrieves a question by its ID.
     *
     * @param id The ID of the question to retrieve.
     * @return The question with the specified ID.
     * @throws RuntimeException if the question is not found.
     */
    @Override
    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Question not found.")
        );
    }

    /**
     * Adds a new question to the quiz.
     *
     * @param type       The type of the question.
     * @param que        The question text.
     * @param option1    Option 1 for the question.
     * @param option2    Option 2 for the question.
     * @param option3    Option 3 for the question.
     * @param option4    Option 4 for the question.
     * @param ans        The correct answer for the question.
     * @param difficulty The difficulty level of the question.
     * @return The newly created Question object.
     */
    @Override
    public Question addQuestion(String type, String que, String option1, String option2, String option3, String option4, String ans, String difficulty) {
        Question question = new Question(type, que, option1, option2,option3, option4, ans, difficulty);

        questionRepository.save(question);
        return question;
    }

    /**
     * Deletes a question from the quiz by its ID.
     *
     * @param id The ID of the question to delete.
     * @return The deleted question.
     * @throws RuntimeException if the question is not found.
     */
    @Override
    public Question deleteQuestion(Integer id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find Question."));

        questionRepository.delete(question);

        return question;
    }
}
