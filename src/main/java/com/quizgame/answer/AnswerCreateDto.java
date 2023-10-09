package com.quizgame.answer;

public record AnswerCreateDto(String correctAnswer, String[] errorAnswers, Integer questionId) {
}
