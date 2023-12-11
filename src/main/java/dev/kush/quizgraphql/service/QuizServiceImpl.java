package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.model.*;
import dev.kush.quizgraphql.repo.QuestionRepository;
import dev.kush.quizgraphql.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class implements the QuizService interface and provides the functionality to manage quizzes in the application.
 */
@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * Creates a new Quiz with the specified title, number of questions, and type.
     *
     * @param title the title of the Quiz
     * @param numQ the number of questions in the Quiz
     * @param type the type of questions to include in the Quiz
     * @return the created Quiz
     */
    @Override
    public Quiz createQuiz(String title, int numQ, String type) {
        List<Question> questions = questionRepository.findRandomQuestionsByType(numQ, type);

        Quiz quiz = new Quiz(title, questions);

        quizRepository.save(quiz);
        return quiz;

    }

    /**
     * Retrieves the list of question wrappers for a quiz by its ID.
     *
     * @param id the ID of the quiz
     * @return a list of QuestionWrapper objects representing the questions in the quiz
     * @throws RuntimeException if no quiz is found with the specified ID
     */
    @Override
    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such quiz found for id " + id)
        );

        List<Question> questionsFromDb = quiz.getQuestions();

        List<QuestionWrapper> questionForUser =
                questionsFromDb.stream()
                        .map(question -> new QuestionWrapper(
                                question.getId(),
                                question.getQue(),
                                question.getOption1(),
                                question.getOption2(),
                                question.getOption3(),
                                question.getOption4()
                        )).toList();

        return questionForUser;
    }

    /**
     * Retrieves all quizzes from the application.
     *
     * @return a list of Quiz objects representing all quizzes in the application
     */
    @Override
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    /**
     * Submits a quiz with the given ID and responses.
     *
     * @param id        the ID of the quiz
     * @param responses the list of responses provided by the user
     * @return a Result object containing the number of correct and incorrect responses
     */
    @Override
    public Result submitQuiz(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such quiz found for id " + id)
        );

        List<Question> questions = quiz.getQuestions();
        questions.sort(Comparator.comparingInt(Question::getId));

        responses.sort(Comparator.comparingInt(Response::id));


        int minSize = Math.min(questions.size(),responses.size());

        int correct = (int) IntStream.range(0,minSize)
                .filter(i -> responses.get(i).ans().trim().
                        equalsIgnoreCase(questions.get(i).getAns().trim()))
                .count();

//        for (Response response:responses){
//            if (response.ans().equals(questions.get(questionNumber).getAns())){
//                correct++;
//            }
//            questionNumber++;
//        }

        return new Result(correct, minSize - correct);
    }

    /**
     * Deletes a Quiz with the specified ID.
     *
     * @param id the ID of the Quiz to delete
     * @return the deleted Quiz
     * @throws RuntimeException if the Quiz with the specified ID is not found
     */
    @Override
    public Quiz deleteQuiz(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Quiz with id " + id + " not found.")
        );

        quizRepository.delete(quiz);
        return quiz;
    }
}
