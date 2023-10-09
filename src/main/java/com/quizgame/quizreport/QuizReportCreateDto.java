package com.quizgame.quizreport;

import com.quizgame.question.LevelQuestion;

import java.util.List;

public record QuizReportCreateDto(
        String username,
        Integer timeLeft,
        LevelQuestion level,
        List<UserAnswers> answers
        ) {
}
