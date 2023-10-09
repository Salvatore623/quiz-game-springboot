package com.quizgame.question;

import lombok.Builder;

import java.util.List;

@Builder
public record QuestionDto(Integer id, String question, List<String> answers) {
}
