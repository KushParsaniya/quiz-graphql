package dev.kush.quizgraphql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Represents a Quiz in the application.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Quiz {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany(cascade = {DETACH,MERGE,REFRESH,PERSIST},fetch = FetchType.EAGER)
    private List<Question> questions;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }
}
