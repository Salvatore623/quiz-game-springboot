package com.quizgame.answer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quizgame.question.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Answer {

    @Id
    @GeneratedValue
    private Integer id;

    private String correctAnswer;

    private String[] errorAnswers = new String[3];

    @OneToOne
    @JsonBackReference
    private Question question;

}
