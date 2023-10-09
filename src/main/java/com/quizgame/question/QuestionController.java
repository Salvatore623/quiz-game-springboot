package com.quizgame.question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("${allowed.origins}")
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {

    private final QuestionService questionService;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @GetMapping("/challenge/{level}/{limit}")
    ResponseEntity<List<QuestionDto>> levelQuestion(@PathVariable LevelQuestion level, @PathVariable int limit){
        return ResponseEntity.ok(questionService.getLevelQuestions(level, limit));
    }

}
