package com.quizgame.quizreport;

import lombok.Builder;

import java.util.List;


@Builder
public record QuizReportResponse(Integer correctUserAnswersCount, Integer score, List<QuizReportCheckAnswerResponseDto> quizReportCheckAnswer) {
}
