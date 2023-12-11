package dev.kush.quizgraphql.repo;

import dev.kush.quizgraphql.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

}
