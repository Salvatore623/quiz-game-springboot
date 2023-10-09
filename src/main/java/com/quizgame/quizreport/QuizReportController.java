package com.quizgame.quizreport;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/quiz-report")
@RequiredArgsConstructor
@CrossOrigin("${allowed.origins}")
public class QuizReportController {

    private final QuizReportService quizReportService;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @PostMapping("/create")
    ResponseEntity<QuizReportResponse> createQuizReport(@RequestBody QuizReportCreateDto quizReport){
        return ResponseEntity.ok( quizReportService.createReportQuiz(quizReport) );
    }

}
