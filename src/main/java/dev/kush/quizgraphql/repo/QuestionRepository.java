package dev.kush.quizgraphql.repo;

import dev.kush.quizgraphql.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query(value = "SELECT * FROM questions q WHERE q.type = :questionType" +
            " ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByType(@Param("numberOfQuestions") int numberOfQuestions, @Param("questionType") String questionType);

}
