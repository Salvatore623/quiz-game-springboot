package com.quizgame.question;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quizgame.answer.Answer;
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
public class Question {

    @Id
    @GeneratedValue
    private Integer id;

    private String question;

    @Enumerated(EnumType.STRING)
    private LevelQuestion level;

    @OneToOne(mappedBy = "question")
    @JsonManagedReference
    private Answer answers;

}
