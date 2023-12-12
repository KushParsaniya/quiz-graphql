package dev.kush.quizgraphql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Represents a question in a quiz.
 */
@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question{
    @Id @GeneratedValue
    private Integer id;
    private String type;
    private String que;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Column(nullable = false)
    private String ans;
    private String difficulty;

    public Question(String type, String que, String option1, String option2, String option3, String option4, String ans, String difficulty) {
        this.type = type;
        this.que = que;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
        this.difficulty = difficulty;
    }
}
