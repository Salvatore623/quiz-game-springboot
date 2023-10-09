package com.quizgame.quizreport;


import lombok.Builder;

@Builder
public record QuizReportCheckAnswerResponseDto(String question, String userAnswer, String correctAnswer) {
}
