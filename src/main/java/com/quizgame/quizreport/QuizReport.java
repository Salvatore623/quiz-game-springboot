package com.quizgame.quizreport;

import com.quizgame.question.LevelQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizReport {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private Integer timeLeft;

    @Enumerated(EnumType.STRING)
    private LevelQuestion level;

    private Integer correctAnswers;

    private Integer score;

}
