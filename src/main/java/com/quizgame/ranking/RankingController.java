package com.quizgame.ranking;

import com.quizgame.question.LevelQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/ranking")
@RequiredArgsConstructor
@CrossOrigin("${allowed.origins}")
public class RankingController {

    private final RankingService rankingService;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @GetMapping("/{level}/{number}")
    ResponseEntity<List<Ranking>> getRanking(@PathVariable LevelQuestion level, @PathVariable int number){
        return ResponseEntity.ok( rankingService.getRanking(level) );
    }


}
