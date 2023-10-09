package com.quizgame.answer;

import com.quizgame.question.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public Answer createAnswer(AnswerCreateDto answerDto){
        var question = questionRepository.findById(answerDto.questionId())
                .orElseThrow(() -> new EntityNotFoundException("Domanda non trovata"));

        Answer answer = Answer.builder()
                .correctAnswer(answerDto.correctAnswer())
                .errorAnswers(answerDto.errorAnswers())
                .question(question)
                .build();

        return answerRepository.save(answer);
    }

}
