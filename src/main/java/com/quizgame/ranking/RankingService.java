package com.quizgame.ranking;

import com.quizgame.question.LevelQuestion;
import com.quizgame.security.user.User;
import com.quizgame.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserRepository userRepository;

    public List<Ranking> getRanking(LevelQuestion level) {
        switch (level) {
            case EASY -> {
                var userList = userRepository.findFirst10ByOrderByBestScoreEasyDesc()
                        .orElseThrow(() -> new EntityNotFoundException("Lista non trovata"));
                return getRankingList(userList, level);
            }
            case MEDIUM -> {
                var userList = userRepository.findFirst10ByOrderByBestScoreMediumDesc()
                        .orElseThrow(() -> new EntityNotFoundException("Lista non trovata"));
                return getRankingList(userList, level);
            }
            case HARD -> {
                var userList = userRepository.findFirst10ByOrderByBestScoreHardDesc()
                        .orElseThrow(() -> new EntityNotFoundException("Lista non trovata"));
                return getRankingList(userList, level);
            }
        }

        return Collections.emptyList();
    }

    private List<Ranking> getRankingList(List<User> userList, LevelQuestion level) {
        List<Ranking> rankingList = new ArrayList<>();
        userList.forEach(user -> {
        var ranking = Ranking.builder()
                    .username(user.getUsername())
                    .avatar(user.getAvatar())
                    .Score(calculateScoreFromLevel(level, user))
                    .level(level)
                    .build();

        rankingList.add(ranking);
        });

        return rankingList;
    }

    private Integer calculateScoreFromLevel(LevelQuestion level, User user) {
        switch (level) {
            case EASY -> {
                return user.getBestScoreEasy();
            }
            case MEDIUM -> {
                return user.getBestScoreMedium();
            }
            case HARD -> {
                return user.getBestScoreHard();
            }
        }
        return 0;
    }
}