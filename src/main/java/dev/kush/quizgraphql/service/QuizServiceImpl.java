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

@Service
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Quiz createQuiz(String title, int numQ, String type) {
        List<Question> questions = questionRepository.findRandomQuestionsByType(numQ, type);

        Quiz quiz = new Quiz(title, questions);

        quizRepository.save(quiz);
        return quiz;

    }

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

    @Override
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

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

    @Override
    public Quiz deleteQuiz(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Quiz with id " + id + " not found.")
        );

        quizRepository.delete(quiz);
        return quiz;
    }
}
