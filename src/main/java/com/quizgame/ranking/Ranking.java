package com.quizgame.ranking;

import com.quizgame.question.LevelQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ranking {

    private String username;
    private String avatar;

    private Integer Score;

    private LevelQuestion level;

}
