package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.model.Question;

import java.io.IOException;

public interface GenerateQuestionService {
    Question generateQuestion(String language,String type) throws IOException, InterruptedException;
}
