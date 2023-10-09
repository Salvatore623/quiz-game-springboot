package com.quizgame.profile;


import lombok.Builder;

@Builder
public record Profile(String username,
                      String avatar,
                      Integer bestScoreEasy,
                      Integer bestScoreMedium,
                      Integer bestScoreHard
) {
}
