package com.quizgame.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/answer")
@RequiredArgsConstructor
@CrossOrigin("${allowed.origins}")
public class AnswerController {

    private final AnswerService answerService;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @PostMapping("/create")
    ResponseEntity<Answer> createAnswer(@RequestBody AnswerCreateDto answer){
        return ResponseEntity.ok( answerService.createAnswer(answer) );
    }

}
